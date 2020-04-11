package view;

import controller.ControlListHotels;
import controller.ControlListTours;
import model.Hotel;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HotelPanelAdd {
    ControlListHotels controlListHotels;
    HotelPanel hotelPanel;
    Hotel hotel;

    private static final String BUTTON_ADD = "Добавить";
    private static final String FIELD_NAME = "Название отеля";
    private static final String FIELD_STARS = "Количество звезд";
    private static final String FIELD_COUNTRY = "Страна нахождения";

    private JButton buttonAdd = new JButton(BUTTON_ADD);
    private JTextField nameField = new JTextField(FIELD_NAME);
    private JTextField starsField = new JTextField(FIELD_STARS);
    private JTextField countryField = new JTextField(FIELD_COUNTRY);


    public HotelPanelAdd(ControlListHotels controlListHotels, HotelPanel hotelPanel) {
        this.controlListHotels=controlListHotels;
        this.hotelPanel=hotelPanel;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        panel.add(nameField);
        panel.add(starsField);
        panel.add(countryField);
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
            java.util.List<Hotel> hotels = controlListHotels.getHotelList();

            int newId = hotels.size() + 1;
            Hotel hotel = new Hotel(nameField.getText(), Integer.parseInt (starsField.getText()), countryField.getText(),
                    newId);
            controlListHotels.addHotel(hotel);
            hotelPanel.updateTable();
        }
    }

}
