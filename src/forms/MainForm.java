package forms;

import utils.DBHandler;
import utils.TableManage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JButton deleteButton;
    private JTable mainTable;
    private JButton editButton;
    private JButton addButton;
    private JPanel mainPanel;

    public MainForm() {

        setTitle("Title");
        setIconImage(new ImageIcon(getClass().getResource("../res/service_logo.png")).getImage());
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1200,900));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultTableModel mainModel = new DefaultTableModel();

        TableManage.refreshTable(mainTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainTable.getSelectedRow()>=0){
                    if (JOptionPane.showConfirmDialog(null,"Вы действительно хотите удалить эту строчку?", "Внимание", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        DBHandler.openConnection();
                        DBHandler.query("DELETE from service WHERE Title='" + mainTable.getValueAt(mainTable.getSelectedRow(), 0) + "'");
                        DBHandler.closeConnection();
                        TableManage.refreshTable(mainTable);
                        mainTable.setModel(mainModel);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Для удаления необходимо выбрать хотя бы одну строчку", "Внимание", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm addForm = new AddForm();
                addForm.setVisible(true);
                addForm.pack();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainTable.getSelectedRow()>=0){
                    if (JOptionPane.showConfirmDialog(null,"Вы действительно хотите редактировать эту строчку?", "Внимание", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        EditForm editForm = new EditForm(mainTable);
                        editForm.setVisible(true);
                        editForm.pack();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Для редактирования необходимо выбрать хотя бы одну строчку", "Внимание", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
