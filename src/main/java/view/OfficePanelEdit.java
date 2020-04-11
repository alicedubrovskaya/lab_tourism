package view;

import controller.ControlListBuros;
import model.Buro;
import model.Office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class OfficePanelEdit {

    ControlListBuros controlListBuros;
    OfficePanel officePanel;
    Buro buro;

    private List<Buro> buros;
    private List<Office> offices;



    private static final String BUTTON_EDIT= "Изменить";
    private static final String ADDRESS_FIELD = "Адрес";
    private static final String NUMBER_FIELD = "Телефон";
    private static final String CONTACT_PERSON_FIELD = "Контактное лицо";
    private static final String MAIL_FIELD= "Почта";

    private JButton buttonEdit = new JButton(BUTTON_EDIT);
    private JTextField addressField = new JTextField(ADDRESS_FIELD);
    private JTextField numberField = new JTextField(NUMBER_FIELD);
    private JTextField contactPersonField = new JTextField(CONTACT_PERSON_FIELD);
    private JTextField mailField = new JTextField(MAIL_FIELD);

    private JComboBox comboBoxGroups=new JComboBox();


    OfficePanelEdit(ControlListBuros controlListBuros, OfficePanel officePanel){
        this.controlListBuros=controlListBuros;
        this.officePanel=officePanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        comboBoxGroups=generateGroups();
        panel.add(comboBoxGroups);
        panel.add(addressField);
        panel.add(numberField);
        panel.add(contactPersonField);
        panel.add(mailField);
        panel.add(buttonEdit);
        panel.doLayout();




        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        buttonEdit.addActionListener(new ButtonEditActionListener());
    }

    class ButtonEditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            Office officeIterator;
            offices=controlListBuros.getAllOffices();
            String addressOfNeededOffice=comboBoxGroups.getSelectedItem().toString();

            for (int i=0;i<offices.size();i++){
                officeIterator=offices.get(i);
                if (officeIterator.getAddress().equals(addressOfNeededOffice)){
                    Office officeEdit= new Office(addressField.getText(), numberField.getText(),contactPersonField.getText(),
                            mailField.getText(), officeIterator.getId());
                    controlListBuros.editOffice(officeEdit, officeIterator.getAddress());
                }
            }
            officePanel.updateTable();
            comboBoxGroups=generateGroups();
        }
    }

    private JComboBox generateGroups(){
        comboBoxGroups.removeAllItems();
        List<Office> offices;
        List<Buro> buros;
        HashSet<String> groups=new HashSet<String>();
        buros=controlListBuros.getBuros();

        for (int i=0;i<buros.size();i++){
            buro=buros.get(i);
            offices=controlListBuros.getAllOffices();
            for (int j=0;j<offices.size();j++){
                groups.add(offices.get(j).getAddress());
            }
        }

        for (String s: groups){
            comboBoxGroups.addItem(s);
        }
        return comboBoxGroups;
    }

}
