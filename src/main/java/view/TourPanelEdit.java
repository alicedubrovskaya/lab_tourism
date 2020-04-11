package view;

import controller.ControlListTours;
import model.Office;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TourPanelEdit {
    ControlListTours controlListTours;
    AllToursPanel allToursPanel;
    Tour tour;

    private static final String BUTTON_EDIT = "Изменить";
    private static final String FIELD_TYPE_OF_TOUR = "Тип тура";
    private static final String FIELD_DURATION = "Продолжительность";
    private static final String FIELD_COUNTRIES = "Страны посещения";
    private static final String FIELD_TRANSPORT = "Транспорт";
    private static final String FIELD_DEPARTURE = "Пункт отправления";

    private JButton buttonEdit = new JButton(BUTTON_EDIT);
    private JTextField typeOfTOurField = new JTextField(FIELD_TYPE_OF_TOUR);
    private JTextField durationField = new JTextField(FIELD_DURATION);
    private JTextField transportField = new JTextField(FIELD_TRANSPORT);
    private JTextField departureField = new JTextField(FIELD_DEPARTURE);
    private JTextField countriesField = new JTextField(FIELD_COUNTRIES);

    private JComboBox comboBoxTours =new JComboBox();
    public TourPanelEdit(ControlListTours controlListTours, AllToursPanel allToursPanel) {
        this.controlListTours = controlListTours;
        this.allToursPanel = allToursPanel;

        comboBoxTours = generateTours();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(comboBoxTours);
        panel.add(typeOfTOurField);
        panel.add(durationField);
        panel.add(transportField);
        panel.add(departureField);
        panel.add(countriesField);
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
            List<Tour> tours;
            List<String> countries=new ArrayList<>();

            Tour tourIterator;
            tours=controlListTours.getTourList();
            int id=Integer.parseInt (comboBoxTours.getSelectedItem().toString());

            String s=countriesField.getText();
            int beginIndex=0;
            for (int i=0;i<s.length();i++){
                if (s.charAt(i)==' '){
                    String t=s.substring(beginIndex,i);
                    beginIndex=i+1;
                    countries.add(t);
                }
            }

            for (int i=0;i<tours.size();i++){
                tourIterator=tours.get(i);
                if (tourIterator.getId()==id){
                    Tour tour = new Tour(typeOfTOurField.getText(), Integer.parseInt (durationField.getText()), transportField.getText(),
                            departureField.getText(), id, countries);
                    controlListTours.editTour(tour,id);
                }
            }
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
                groups.add(Integer.toString (tour.getId()));
            }

        for (String s: groups){
            comboBoxTours.addItem(s);
        }
        return comboBoxTours;
    }

}
