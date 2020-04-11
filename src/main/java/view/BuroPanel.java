package view;

import controller.ControlListBuros;
import model.Buro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuroPanel {

    private MainPanel main;
    private  ControlListBuros controlListBuros;
    private List<Buro> buros;

    private int countOfBuros;


    private JPanel buroPanel=new JPanel();
    private DefaultTableModel model = new DefaultTableModel(8,3);


    private static final String SET_TITLE = "";


    BuroPanel(ControlListBuros controlListBuros, MainPanel main){
        this.main=main;
        this.controlListBuros=controlListBuros;

        Object[] columnsHeader = new String[]{"Название","ФИО владельца","Телефон"};
        model.setColumnIdentifiers(columnsHeader);
        JTable buroTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(buroTable);
        buroPanel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(500,200));
        buroTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        buroTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        buroTable.getColumnModel().getColumn(1).setPreferredWidth(190);
        buroTable.getColumnModel().getColumn(2).setPreferredWidth(150);

        updateTable();


        JDialog dialog = new JDialog();
        dialog.add(buroPanel);
        dialog.setTitle(SET_TITLE);
        dialog.setResizable(false);
        dialog.setSize(600, 600);
        dialog.setVisible(true);
    }


    void updateTable(){
        Buro buro;
        buros=controlListBuros.getBuros();

        countOfBuros=buros.size();
        model.setRowCount(0);

        for (int i=0;i<countOfBuros;i++){
            buro=buros.get(i);
            Object[] row = new Object[]{
                    buro.getName(),
                    buro.getFullName(),
                    buro.getNumber(),
            };
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }


}
