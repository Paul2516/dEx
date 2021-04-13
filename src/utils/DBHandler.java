package utils;

import javax.swing.*;
import java.sql.*;
import java.util.Locale;


/*
 "jdbc:mysql://localhost:3306/serviceaotu",
 "root",
 "123456"
*/
public class DBHandler extends JFrame {

    public static Connection connection;

    public static boolean openConnection(){
        try {
            connection = DriverManager.getConnection(
                    Constructor.DBURL,
                    Constructor.DBUSER,
                    Constructor.DBPASSWORD
            );
            return true;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean closeConnection(){
        try {
            connection.close();
            return true;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    public static ResultSet query(String query){
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (query.contains("SELECT"))
                resultSet = preparedStatement.executeQuery();
            else {
                try {
                    preparedStatement.executeUpdate();
                }
                catch (SQLException throwables){
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Невозможно удалить строку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
