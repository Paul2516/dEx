package forms;

import javax.swing.*;
import java.awt.*;

public class AddForm extends JFrame {
    private JPanel AddPanel;
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

    /**
     *
     * @param Table
     * @param title
     * @param windowTitle
     */
    public AddForm(JTable Table, String title, String windowTitle) {

        setMinimumSize(new Dimension(900, 600));
        setIconImage(new ImageIcon(getClass().getResource("../res/service_logo.png")).getImage());
        setContentPane(AddPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("ТИТУЛ");

    }
}
