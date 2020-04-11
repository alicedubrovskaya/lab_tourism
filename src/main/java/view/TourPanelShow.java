package view;

import controller.ControlListTours;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class TourPanelShow {
    ControlListTours controlListTours;
    AllToursPanel allToursPanel;
    Tour tour;

    private static final String FIELD_TYPE_OF_TOUR = "Тип тура";
    private static final String FIELD_DURATION = "Продолжительность";
    private static final String FIELD_COUNTRIES = "Страны посещения";
    private static final String FIELD_TRANSPORT = "Транспорт";
    private static final String FIELD_DEPARTURE = "Пункт отправления";
    private static final String FIELD_ID = "Номер";


    JLabel idLabel=new JLabel(FIELD_ID);
    JLabel typeLabel = new JLabel(FIELD_TYPE_OF_TOUR);
    JLabel durationLabel=new JLabel(FIELD_DURATION);
    JLabel countriesLabel=new JLabel(FIELD_COUNTRIES);
    JLabel transportLabel=new JLabel(FIELD_TRANSPORT);
    JLabel departureLabel=new JLabel(FIELD_DEPARTURE);
    private JComboBox comboBoxTours =new JComboBox();


    TourPanelShow(ControlListTours controlListTours, AllToursPanel allToursPanel){
        this.controlListTours=controlListTours;
        this.allToursPanel=allToursPanel;

        comboBoxTours = generateTours();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(comboBoxTours);
        panel.add(idLabel);
        panel.add(typeLabel);
        panel.add(durationLabel);
        panel.add(countriesLabel);
        panel.add(transportLabel);
        panel.add(departureLabel);


        panel.doLayout();


        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        comboBoxTours.addActionListener(new ComboBoxActionListener());
    }

    class ComboBoxActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {

            tour=controlListTours.getTour(Integer.parseInt (comboBoxTours.getSelectedItem().toString()));
            idLabel.setText("Номер тура: "+tour.getId());
            typeLabel.setText("Тип тура: "+tour.getTypeOfTour());
            durationLabel.setText("Продолжительность тура: " +Integer.toString (tour.getDuration()));
            transportLabel.setText("Транспорт: "+ tour.getTransport());
            departureLabel.setText("Пункт отправления: "+tour.getDeparture());
            List<String> countries=tour.getCountriesVisit();
            String s="";
            for (int i=0;i<countries.size();i++){
                if (i!=countries.size()-1) {
                    s+=countries.get(i)+",";
                }
                else s+=countries.get(i);
            }
            countriesLabel.setText("Страны посещения: "+s);

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
