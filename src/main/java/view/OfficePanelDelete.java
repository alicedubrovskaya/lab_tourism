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

public class OfficePanelDelete {
    ControlListBuros controlListBuros;
    OfficePanel officePanel;
    Buro buro;

    private List<Buro> buros;
    private List<Office> offices;

    private JComboBox comboBoxGroups=new JComboBox();


    public OfficePanelDelete(ControlListBuros controlListBuros, OfficePanel officePanel){
        this.controlListBuros=controlListBuros;
        this.officePanel=officePanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        comboBoxGroups=generateGroups();
        panel.add(comboBoxGroups);
        panel.doLayout();


        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        comboBoxGroups.addActionListener(new ComboBoxDeleteActionListener());
    }

    class ComboBoxDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            controlListBuros.deleteOffice(comboBoxGroups.getSelectedItem().toString());
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
