package dao;

import model.Buro;
import model.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeDao {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private static OfficeDao instance;

    private OfficeDao(){
    }

    public static OfficeDao getInstance(){
        if(instance==null){
            instance = new OfficeDao();
        }
        return instance;
    }

    public List<Office> getAll() {
        List<Office> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("Select * from offices");
            result = statement.executeQuery();
            while(result.next()){
                list.add(new Office(result.getString(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getInt(5)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(офисы)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(офисы)" + e);
            }
        }
        return list;
    }

    public void add(Office office) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("INSERT INTO offices (address, number, contactPerson, mail,id) VALUES (?, ?, ?, ?,?)");
            statement.setString(1, office.getAddress());
            statement.setString(2, office.getNumber());
            statement.setString(3, office.getContactPerson());
            statement.setString(4, office.getMail());
            statement.setInt(5, office.getId());
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

    public void delete(String address) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("DELETE FROM offices WHERE address=?");
            statement.setString(1, address);
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

    public void edit(Office office, String address) {
        delete(address);
        add(office);
    }

}
