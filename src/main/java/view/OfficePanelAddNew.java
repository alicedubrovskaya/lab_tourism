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

public class OfficePanelAddNew {
    ControlListBuros controlListBuros;
    OfficePanel officePanel;
    Buro buro;

    private List<Buro> buros;
    private List<Office> offices;



    private static final String BUTTON_ADD= "Добавить";
    private static final String ADDRESS_FIELD = "Адрес";
    private static final String NUMBER_FIELD = "Телефон";
    private static final String CONTACT_PERSON_FIELD = "Контактное лицо";
    private static final String MAIL_FIELD= "Почта";

    private JButton buttonAdd = new JButton(BUTTON_ADD);
    private JTextField addressField = new JTextField(ADDRESS_FIELD);
    private JTextField numberField = new JTextField(NUMBER_FIELD);
    private JTextField contactPersonField = new JTextField(CONTACT_PERSON_FIELD);
    private JTextField mailField = new JTextField(MAIL_FIELD);

    private JComboBox comboBoxGroups=new JComboBox();


    OfficePanelAddNew(ControlListBuros controlListBuros, OfficePanel officePanel){
        this.controlListBuros=controlListBuros;
        this.officePanel=officePanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(addressField);
        panel.add(numberField);
        panel.add(contactPersonField);
        panel.add(mailField);
        comboBoxGroups=generateGroups();
        panel.add(comboBoxGroups);
        panel.doLayout();



        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        comboBoxGroups.addActionListener(new ComboBoxAddActionListener());
    }



    class ComboBoxAddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            Buro buro;
            Buro buroForAdding;
            int idOfNeededBuro=0;
            buros=controlListBuros.getBuros();
            String nameOfNeededBuro=comboBoxGroups.getSelectedItem().toString();
            for (int i=0;i<buros.size();i++){
                buro=buros.get(i);
                if (buro.getName().equals(nameOfNeededBuro)){
                    idOfNeededBuro=buro.getId();
                }
            }
            Office officeForIteration;
            offices=controlListBuros.getAllOffices();

            for (int i=0;i<offices.size();i++){
                officeForIteration=offices.get(i);
                if (officeForIteration.getId()==idOfNeededBuro) {
                    Office office= new Office(addressField.getText(), numberField.getText(),contactPersonField.getText(),
                            mailField.getText(), idOfNeededBuro);
                    controlListBuros.addOffice(office);
                }
            }
            officePanel.updateTable();
        }
    }
        private JComboBox generateGroups(){
            comboBoxGroups.removeAllItems();
            List<Buro> buros;
            HashSet<String> groups=new HashSet<String>();
            buros=controlListBuros.getBuros();

            for (int i=0;i<buros.size();i++){
                buro=buros.get(i);
                    groups.add(buro.getName());
            }

            for (String s: groups){
                comboBoxGroups.addItem(s);
            }
            return comboBoxGroups;
        }
}

