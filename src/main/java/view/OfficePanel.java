package view;

import controller.ControlListBuros;
import model.Buro;
import model.Office;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class OfficePanel {
    private static final String BUTTON_ADD_OFFICE = "Добавить офис";
    private static final String BUTTON_DELETE = "Удалить офис";
    private static final String BUTTON_EDIT = "Редактировать офис";


    private static final String SET_TITLE = "Офисы";

    private MainPanel main;
    private ControlListBuros controlListBuros;
    private List<Office> offices = new ArrayList<Office>();
    private List<Buro> buros;
    private JButton buttonDelete = new JButton(BUTTON_DELETE);
    private JButton buttonEdit = new JButton(BUTTON_EDIT);


    private int countOfOffices;
    private int countOfBuros;


    private JPanel officePanel = new JPanel();
    JButton buttonAddOffice = new JButton(BUTTON_ADD_OFFICE);
    private DefaultTableModel model = new DefaultTableModel(8, 5);

    OfficePanel(ControlListBuros controlListBuros, MainPanel main) {
        this.main = main;
        this.controlListBuros = controlListBuros;
        officePanel.add(buttonAddOffice);
        officePanel.add(buttonDelete);
        officePanel.add(buttonEdit);


        Object[] columnsHeader = new String[]{"Адрес", "Телефон","Почта","Контактное лицо","Бюро"};
        model.setColumnIdentifiers(columnsHeader);
        JTable officeTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(officeTable);
        officePanel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        officeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        updateTable();


        JDialog dialog = new JDialog();
        dialog.add(officePanel);
        dialog.setTitle(SET_TITLE);
        dialog.setResizable(false);
        dialog.setSize(700, 600);
        dialog.setVisible(true);

        buttonAddOffice.addActionListener(new ButtonAddOfficeActionListener());
        buttonDelete.addActionListener(new ButtonDeleteActionListener());
        buttonEdit.addActionListener(new ButtonEditActionListener());

    }

    void updateTable() {
        Buro buro;
        Office office;

        buros = controlListBuros.getBuros();
        countOfBuros = buros.size();

        offices = controlListBuros.getAllOffices();
        countOfOffices = offices.size();

        model.setRowCount(0);

        for (int i = 0; i < countOfOffices; i++) {
            office = offices.get(i);
            for (int j = 0; j < countOfBuros; j++) {
                buro = buros.get(j);
                if (buro.getId() == office.getId()) {
                    Object[] row = new Object[]{
                            office.getAddress(),
                            office.getNumber(),
                            office.getMail(),
                            office.getContactPerson(),
                            buro.getName(),
                    };
                    model.addRow(row);
                }
            }
            model.fireTableDataChanged();
        }
    }
    class ButtonAddOfficeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            OfficePanelAddNew officePanelAddNew = new OfficePanelAddNew(controlListBuros, OfficePanel.this);
        }
    }

    class ButtonDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
             OfficePanelDelete officePanelDelete = new OfficePanelDelete(controlListBuros, OfficePanel.this);
        }
    }

    class ButtonEditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
             OfficePanelEdit officePanelEdit=new OfficePanelEdit(controlListBuros, OfficePanel.this);
        }
    }
}
