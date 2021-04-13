package forms;

import utils.DBHandler;
import utils.TableManage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainForm extends JFrame {
    private JButton deleteButton;
    private JTable mainTable;
    private JButton editButton;
    private JButton addButton;
    private JPanel mainPanel;

    public MainForm() {

        setTitle("Title");
        setMinimumSize(new Dimension(1200, 900));
        setContentPane(mainPanel);
        setIconImage(new ImageIcon(getClass().getResource("../res/service_logo.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel mainModel = new DefaultTableModel();

        mainModel.setColumnIdentifiers(new String[]{
                "",
                "",
                "",
                "",
                "",
                "",
        });

        DBHandler.openConnection();
        ResultSet resultSet = DBHandler.query("SELECT * from service");
        try {
            while (resultSet.next()){
                mainModel.addRow(new String[]{
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
        mainTable.setModel(mainModel);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (mainTable.getSelectedRow()>=0){
                    if (JOptionPane.showConfirmDialog(null,"Вы действительно хотите удалить эту строчку?","Внимание",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        DBHandler.openConnection();
                        DBHandler.query("DELETE from service WHERE Title='" + mainTable.getValueAt(mainTable.getSelectedRow(), 0) + "'");
                        DBHandler.closeConnection();
                        TableManage.refreshTable(mainTable);
                        mainTable.setModel(mainModel);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Для удаления необходимо выбрать хотя бы одну строчку", "Внимание", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm addForm = new AddForm(mainTable, "", "");
                addForm.setVisible(true);
                addForm.pack();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainTable.getSelectedRow()>=0){
                    if (JOptionPane.showConfirmDialog(null,"Вы действительно хотите редактировать эту строчку?","Внимание",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        EditForm editForm = new EditForm(mainTable, "", "");
                        editForm.setVisible(true);
                        editForm.pack();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Для редактирования необходимо выбрать хотя бы одну строчку", "Внимание", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


    }
}
