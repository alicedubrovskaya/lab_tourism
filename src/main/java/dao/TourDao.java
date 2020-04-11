package dao;

import model.Buro;
import model.Office;
import model.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDao {
    private Connection connection;
    private PreparedStatement statement;
    private PreparedStatement statementCountries;
    private ResultSet result;
    private ResultSet resultCounrties;
    private Connection connectionCountries;

    private static TourDao instance;

    private TourDao(){
    }

    public static TourDao getInstance(){
        if(instance==null){
            instance = new TourDao();
        }
        return instance;
    }

    public List<String> getToursOnDate(String date) {
        List<String> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement(
                    "Select count, dateOfDeparture, cost, fullname, number, name from tour_info JOIN buro\n" +
                    "ON tour_info.buro_id=buro.id\n" +
                    "WHERE dateOfDeparture=?;\n");
            statement.setString(1, date);
            result = statement.executeQuery();
            while(result.next()){
                String s="";
                s=Integer.toString (result.getInt(1))+", "+result.getString(2)
                        +", "+Integer.toString (result.getInt(3))+", "+result.getString(4)
                        +", "+result.getString(5)+", "+result.getString(6);
                list.add(s);
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием" + e);
            }
        }
        return list;
    }


    public List<Tour> getAll() {
        List<Tour> list=new ArrayList<>();

        try {
            connection = DatabaseConnector.getInstance().getConnection();
            connectionCountries=DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("Select typeOfTour, duration, transport, departure, id from tours;");
            result = statement.executeQuery();
            while(result.next()){
                statementCountries=connectionCountries.prepareStatement("Select * from tour_country;");
                resultCounrties=statementCountries.executeQuery();
                List<String> listCountries=new ArrayList<>();
                while(resultCounrties.next()){
                    if (resultCounrties.getInt(1)==result.getInt(5)){
                        listCountries.add(resultCounrties.getString(2));
                    }
                }
                list.add(new Tour(result.getString(1), result.getInt(2),
                        result.getString(3),result.getString(4), result.getInt(5),
                        listCountries));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(туры)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(туры)" + e);
            }
        }
        return list;
    }

    public Tour getTour(int id) {
        List<Tour> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT typeOfTour, duration, transport, departure, id FROM tours WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            statement = connection.prepareStatement("Select * from tour_country WHERE tour_id=?");
            statement.setInt(1, id);
            resultCounrties=statement.executeQuery();

            List<String> listCountries=new ArrayList<>();
               while(resultCounrties.next()){
                        listCountries.add(resultCounrties.getString(2));
                }
               while (result.next()) {
                   Tour tour = new Tour(result.getString(1), result.getInt(2),
                           result.getString(3), result.getString(4), id,
                           listCountries);
                   list.add(tour);
               }

        }catch (SQLException e){
            System.out.println("Проблемы с бд(туры)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(туры)" + e);
            }
        }
        return list.get(0);
    }



    public void delete(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("DELETE FROM tours WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement=connection.prepareStatement("DELETE FROM tour_country WHERE tour_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Проблемы с удалением из бд");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием удаления в бд");
            }
        }
    }

    public void add(Tour tour) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("INSERT INTO tours (typeOfTour, duration, transport, departure, id) VALUES (?, ?, ?, ?,?)");
            statement.setString(1, tour.getTypeOfTour());
            statement.setInt(2, tour.getDuration());
            statement.setString(3, tour.getTransport());
            statement.setString(4, tour.getDeparture());
            statement.setInt(5, tour.getId());
            statement.executeUpdate();

            List<String> countries= tour.getCountriesVisit();
            for (int i=0;i<countries.size();i++) {
                statement = connection.prepareStatement("INSERT INTO tour_country(tour_id,country) VALUES (?,?)");
                statement.setInt(1, tour.getId());
                statement.setString(2, countries.get(i));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с записью бд" + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Проблемы с закрытием записи в бд" + e);
            }
        }
    }

    public void edit(Tour tour, int id) {
        delete(id);
        add(tour);
    }

}
