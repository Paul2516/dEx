package utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableManage {
    public static void refreshTable(JTable table) {
        DefaultTableModel manageModel = new DefaultTableModel();
        DBHandler.openConnection();
        ResultSet resultSet = DBHandler.query("SELECT * from service");
        try {
            while (resultSet.next()){
                manageModel.addRow(new String[]{
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                });
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        DBHandler.closeConnection();
        table.setModel(manageModel);

    }
}
