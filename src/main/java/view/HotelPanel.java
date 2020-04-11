package view;

import controller.ControlListBuros;
import controller.ControlListHotels;
import model.Buro;
import model.Hotel;
import model.Office;
import model.Tour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HotelPanel {
    private static final String BUTTON_ADD = "Добавить отель";
    private static final String BUTTON_DELETE = "Удалить отель";
    private static final String BUTTON_EDIT = "Редактировать отель";
    private static final String SET_TITLE = "Отели";
    private static final String SET_STARS = "Все пятизвездочные отели, через которые проходят туры";



    private MainPanel main;
    private ControlListHotels controlListHotels;

    private List<Hotel> hotels = new ArrayList<Hotel>();

    private JButton buttonDelete = new JButton(BUTTON_DELETE);
    private JButton buttonEdit = new JButton(BUTTON_EDIT);
    private JButton buttonAdd = new JButton(BUTTON_ADD);
    private JButton buttonShowStars = new JButton(SET_STARS);

    JLabel hotelLabel=new JLabel("Отели пятизвездочные");

    private JPanel hotelPanel = new JPanel();
    private DefaultTableModel model = new DefaultTableModel(8, 4);

    HotelPanel(ControlListHotels controlListHotels, MainPanel main){
        this.main = main;
        this.controlListHotels=controlListHotels;
        hotelPanel.add(buttonAdd);
        hotelPanel.add(buttonDelete);
        hotelPanel.add(buttonEdit);
        hotelPanel.add(buttonShowStars);
        hotelPanel.add(hotelLabel);


        Object[] columnsHeader = new String[]{"Номер","Название", "Количество звезд","Страна нахождения"};
        model.setColumnIdentifiers(columnsHeader);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        hotelPanel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        updateTable();


        JDialog dialog = new JDialog();
        dialog.add(hotelPanel);
        dialog.setTitle(SET_TITLE);
        dialog.setResizable(false);
        dialog.setSize(700, 600);
        dialog.setVisible(true);

        buttonAdd.addActionListener(new ButtonAddActionListener());
        buttonDelete.addActionListener(new ButtonDeleteActionListener());
        buttonEdit.addActionListener(new ButtonEditActionListener());
        buttonShowStars.addActionListener(new ButtonShowStarsActionListener());
    }
    void updateTable() {
        Hotel hotel;
        hotels=controlListHotels.getHotelList();

        int countOfHotels=hotels.size();
        model.setRowCount(0);

        for (int i=0;i<countOfHotels;i++){
            hotel=hotels.get(i);
            Object[] row = new Object[]{
                    Integer.toString(hotel.getId()),
                    hotel.getName(),
                    Integer.toString (hotel.getStars()),
                    hotel.getCountryLocation(),
            };
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }

    class ButtonAddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
           HotelPanelAdd hotelPanelAdd=new HotelPanelAdd(controlListHotels, HotelPanel.this);
        }
    }

    class ButtonDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            HotelPanelDelete hotelPanelDelete=new HotelPanelDelete(controlListHotels,HotelPanel.this);
        }
    }

    class ButtonEditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            HotelPanelEdit hotelPanelEdit=new HotelPanelEdit(controlListHotels,HotelPanel.this);
        }
    }
    class ButtonShowStarsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            List<String> hotelsFiveStars;
            hotelsFiveStars=controlListHotels.getHotelFiveStarsList();
            String s="";
            for (int i=0;i<hotelsFiveStars.size();i++){
                if (i!=hotelsFiveStars.size()-1){
                    s+=hotelsFiveStars.get(i)+", ";
                }
                else s+=hotelsFiveStars.get(i);
            }
            hotelLabel.setText(s);
        }
    }
}
