package view;

import controller.ControlListHotels;
import model.Hotel;
import model.Tour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class HotelPanelDelete {
    ControlListHotels controlListHotels;
    HotelPanel hotelPanel;
    Hotel hotel;

    private JComboBox comboBoxHotels =new JComboBox();

    public HotelPanelDelete(ControlListHotels controlListHotels,  HotelPanel hotelPanel){
       this.controlListHotels=controlListHotels;
       this.hotelPanel=hotelPanel;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
        comboBoxHotels = generateHotels();
        panel.add(comboBoxHotels);
        panel.doLayout();


        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setTitle("");
        dialog.setResizable(false);
        dialog.setSize(400, 600);
        dialog.setVisible(true);

        comboBoxHotels.addActionListener(new ComboBoxDeleteActionListener());
    }

    class ComboBoxDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            controlListHotels.deleteHotel(Integer.parseInt (comboBoxHotels.getSelectedItem().toString()));
            hotelPanel.updateTable();
            comboBoxHotels = generateHotels();
        }
    }

    private JComboBox generateHotels(){
        comboBoxHotels.removeAllItems();
        List<Hotel> hotels;
        HashSet<String> groups=new HashSet<String>();
        hotels=controlListHotels.getHotelList();

        for (int i=0;i<hotels.size();i++){
            hotel=hotels.get(i);
            int hotelId=hotel.getId();
            groups.add(Integer.toString(hotelId));
        }

        for (String s: groups){
            comboBoxHotels.addItem(s);
        }
        return comboBoxHotels;
    }
}
