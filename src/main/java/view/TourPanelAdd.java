package view;

import controller.ControlListTours;
import model.Buro;
import model.Office;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TourPanelAdd {
    ControlListTours controlListTours;
    AllToursPanel allToursPanel;
    Tour tour;

    private static final String BUTTON_ADD = "Добавить";
    private static final String FIELD_TYPE_OF_TOUR = "Тип тура";
    private static final String FIELD_DURATION = "Продолжительность";
    private static final String FIELD_COUNTRIES = "Страны посещения";
    private static final String FIELD_TRANSPORT = "Транспорт";
    private static final String FIELD_DEPARTURE = "Пункт отправления";

    private JButton buttonAdd = new JButton(BUTTON_ADD);
    private JTextField typeOfTOurField = new JTextField(FIELD_TYPE_OF_TOUR);
    private JTextField durationField = new JTextField(FIELD_DURATION);
    private JTextField transportField = new JTextField(FIELD_TRANSPORT);
    private JTextField departureField = new JTextField(FIELD_DEPARTURE);
    private JTextField countriesField = new JTextField(FIELD_COUNTRIES);


    public TourPanelAdd(ControlListTours controlListTours, AllToursPanel allToursPanel) {
        this.controlListTours = controlListTours;
        this.allToursPanel = allToursPanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(typeOfTOurField);
        panel.add(durationField);
        panel.add(transportField);
        panel.add(departureField);
        panel.add(countriesField);
        panel.add(buttonAdd);

        panel.doLayout();


        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        buttonAdd.addActionListener(new ButtonAddActionListener());
    }

    class ButtonAddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            List<Tour> tours = controlListTours.getTourList();
            List<String> countries=new ArrayList<>();

            int newId = tours.size() + 1;
            String s=countriesField.getText();
            int beginIndex=0;
            for (int i=0;i<s.length();i++){
                if (s.charAt(i)==' '){
                    String t=s.substring(beginIndex,i);
                    beginIndex=i+1;
                    countries.add(t);
                }
            }

           Tour tour = new Tour(typeOfTOurField.getText(), Integer.parseInt (durationField.getText()), transportField.getText(),
                    departureField.getText(), newId, countries);
            controlListTours.addTour(tour);
            allToursPanel.updateTable();
        }
    }
}
