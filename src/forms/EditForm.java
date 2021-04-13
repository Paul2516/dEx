package forms;

import utils.DBHandler;

import javax.swing.*;
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

    public EditForm(JTable mainTable) {

        setTitle("Title");
        setIconImage(new ImageIcon(getClass().getResource("../res/service_logo.png")).getImage());
        setContentPane(EditPanel);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        DBHandler.openConnection();
        ResultSet resultSet = DBHandler.query("SELECT * from service WHERE Title='" + mainTable.getValueAt(mainTable.getSelectedRow(), 0) + "'");
        try {
            while (resultSet.next()){
                SecondNameField.setText(resultSet.getString(2));
                FirstNameField.setText(resultSet.getString(3));
                PatronymicField.setText(resultSet.getString(4));
                BirthdayField.setText(resultSet.getString(5));
                RegDateField.setText(resultSet.getString(6));
                EmailField.setText(resultSet.getString(7));
                MobileField.setText(resultSet.getString(8));
                GenderField.setText(resultSet.getString(9));
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        DBHandler.closeConnection();

    }
}
