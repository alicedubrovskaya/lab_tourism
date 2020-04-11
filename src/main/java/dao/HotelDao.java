package dao;

import model.Buro;
import model.Hotel;
import model.Office;
import model.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private static HotelDao instance;

    private HotelDao(){
    }

    public static HotelDao getInstance(){
        if(instance==null){
            instance = new HotelDao();
        }
        return instance;
    }

    public List<Hotel> getAll() {
        List<Hotel> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("Select * from hotels");
            result = statement.executeQuery();
            while(result.next()){
                list.add(new Hotel(result.getString(1), result.getInt(2), result.getString(3),
                        result.getInt(4)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(отели)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(отели)" + e);
            }
        }
        return list;
    }

    public void add(Hotel hotel) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("INSERT INTO hotels (name, stars, countryLocation, id) VALUES (?, ?, ?,?)");
            statement.setString(1, hotel.getName());
            statement.setInt(2,hotel.getStars());
            statement.setString(3,hotel.getCountryLocation());
            statement.setInt(4,hotel.getId());
            statement.executeUpdate();
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

    public void delete(int id) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("DELETE FROM hotels WHERE id=?");
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

    public void edit(Hotel hotel, int id) {
        delete(id);
        add(hotel);
    }

    public List<String> getFiveStarsHotels() {
        List<String> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT hotels.name FROM\n" +
                    "tour_country JOIN hotels ON tour_country.country=hotels.countryLocation\n" +
                    "WHERE hotels.stars=5;");
            result = statement.executeQuery();
            while(result.next()){
                list.add(result.getString(1));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(отели)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(отели)" + e);
            }
        }
        return list;
    }




}
