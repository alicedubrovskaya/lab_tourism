package view;

import controller.ControlListTours;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class TourPanelDelete {
    ControlListTours controlListTours;
    AllToursPanel allToursPanel;
    Tour tour;

    private JComboBox comboBoxTours =new JComboBox();

    public TourPanelDelete(ControlListTours controlListTours, AllToursPanel allToursPanel){
        this.controlListTours=controlListTours;
        this.allToursPanel=allToursPanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        comboBoxTours = generateTours();
        panel.add(comboBoxTours);
        panel.doLayout();


        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        comboBoxTours.addActionListener(new ComboBoxDeleteActionListener());
    }

    class ComboBoxDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
           controlListTours.deleteTour(comboBoxTours.getSelectedItem().toString());
            allToursPanel.updateTable();
            comboBoxTours = generateTours();
        }
    }

    private JComboBox generateTours(){
        comboBoxTours.removeAllItems();
        List<Tour> tours;
        HashSet<String> groups=new HashSet<String>();
        tours=controlListTours.getTourList();

        for (int i=0;i<tours.size();i++){
            tour=tours.get(i);
            int tourId=tour.getId();
            groups.add(Integer.toString (tourId));
        }

        for (String s: groups){
            comboBoxTours.addItem(s);
        }
        return comboBoxTours;
    }
}
