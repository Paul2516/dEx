package forms;

import utils.DBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditForm extends JFrame {
    private JPanel EditPanel;
    private JTextField SecondNameField;
    private JTextField FirstNameField;
    private JTextField PatronymicField;
    private JTextField BirthdayField;
    private JTextField RegDateField;
    private JTextField EmailField;
    private JTextField MobileField;
    private JTextField GenderField;
    private JTextField PhotoField;
    private JLabel LastName;
    private JLabel Name;
    private JLabel Patronymic;
    private JLabel Birthday;
    private JLabel RegDate;
    private JLabel Email;
    private JLabel Mobile;
    private JLabel Gender;
    private JLabel Photo;

    public EditForm(JTable mainTable, String Title, String WindowsTitle) {

        setTitle("Title");
        setMinimumSize(new Dimension(900, 600));
        setContentPane(EditPanel);
        setIconImage(new ImageIcon(getClass().getResource("../res/service_logo.png")).getImage());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel editModel = new DefaultTableModel();
        DBHandler.openConnection();
        ResultSet resultSet = DBHandler.query("SELECT * from service WHERE Title='" + mainTable.getValueAt(mainTable.getSelectedRow(), 0) + "'");
        try {
            while (resultSet.next()) {
                editModel.addRow(new String[]{
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBHandler.closeConnection();
        mainTable.setModel(editModel);
    }
}
