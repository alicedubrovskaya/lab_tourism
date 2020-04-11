package view;

import controller.ControlListHotels;
import model.Hotel;
import model.Office;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HotelPanelEdit {
    ControlListHotels controlListHotels;
    HotelPanel hotelPanel;
    Hotel hotel;
    private List<Hotel> hotels;


    private static final String BUTTON_EDIT = "Изменить";
    private static final String FIELD_NAME = "Название отеля";
    private static final String FIELD_STARS = "Количество звезд";
    private static final String FIELD_COUNTRY = "Страна нахождения";

    private JButton buttonEdit = new JButton(BUTTON_EDIT);
    private JTextField nameField = new JTextField(FIELD_NAME);
    private JTextField starsField = new JTextField(FIELD_STARS);
    private JTextField countryField = new JTextField(FIELD_COUNTRY);

    private JComboBox comboBoxHotels =new JComboBox();
    public HotelPanelEdit( ControlListHotels controlListHotels, HotelPanel hotelPanel) {
        this.controlListHotels=controlListHotels;
        this.hotelPanel=hotelPanel;

        comboBoxHotels = generateHotels();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(comboBoxHotels);
        panel.add(nameField);
        panel.add(starsField);
        panel.add(countryField);
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
            Hotel hotelIterator;
            hotels=controlListHotels.getHotelList();
            int idOfNeededHotel=Integer.parseInt (comboBoxHotels.getSelectedItem().toString());

            for (int i=0;i<hotels.size();i++){
                hotelIterator=hotels.get(i);
                if (hotel.getId()==idOfNeededHotel){
                    Hotel hotelEdit= new Hotel(nameField.getText(), Integer.parseInt (starsField.getText()),countryField.getText(),
                            idOfNeededHotel);
                    controlListHotels.editHotel(hotelEdit, idOfNeededHotel);
                }
            }
            hotelPanel.updateTable();
            comboBoxHotels=generateHotels();
        }
    }


        private JComboBox generateHotels() {
            comboBoxHotels.removeAllItems();
            List<Hotel> hotels;
            HashSet<String> groups = new HashSet<String>();
            hotels = controlListHotels.getHotelList();

            for (int i = 0; i < hotels.size(); i++) {
                hotel = hotels.get(i);
                groups.add(Integer.toString(hotel.getId()));
            }

            for (String s : groups) {
                comboBoxHotels.addItem(s);
            }
            return comboBoxHotels;
        }
}
