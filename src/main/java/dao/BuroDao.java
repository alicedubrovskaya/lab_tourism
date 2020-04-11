package dao;

import model.Buro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuroDao {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private static BuroDao instance;

    private BuroDao(){
    }

    public static BuroDao getInstance(){
        if(instance==null){
            instance = new BuroDao();
        }
        return instance;
    }

    public List<Buro> getAll() {
        List<Buro> list=new ArrayList<>();
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("Select * from buro");
            result = statement.executeQuery();
            while(result.next()){
                list.add(new Buro(result.getString(1), result.getString(2),
                        result.getString(3),result.getInt(4)));
            }
        }catch (SQLException e){
            System.out.println("Проблемы с бд(бюро)" + e);
        }finally {
            try {
                statement.close();
                result.close();
            }catch(SQLException e){
                System.out.println("Проблемы с закрытием(бюро)" + e);
            }
        }
        return list;
    }

    public void add(Buro buro) {
        try {
            connection = DatabaseConnector.getInstance().getConnection();
            statement = connection.prepareStatement("INSERT INTO buro (fullname, number, name, id) VALUES (?, ?, ?,?)");
            statement.setString(1, buro.getFullName());
            statement.setString(2, buro.getNumber());
            statement.setString(3, buro.getName());
            statement.setInt(4, buro.getId());
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
}
