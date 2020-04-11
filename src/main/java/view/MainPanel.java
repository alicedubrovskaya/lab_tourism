package view;

import controller.ControlListBuros;
import controller.ControlListHotels;
import controller.ControlListTours;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainPanel extends JFrame {
    private ControlListBuros controlListBuros;
    private ControlListTours controlListTours;
    private  ControlListHotels controlListHotels;

    private static final String WINDOW ="Окно";
    private static final String BUTTON_ONE ="Все бюро";
    private static final String BUTTON_ALL_OFFICES ="Все офисы";
    private static final String BUTTON_ALL_TOURS ="Все туры";
    private static final String BUTTON_ALL_HOTELS ="Все отели";
    private static final String BUTTON_PRICE_LIST ="Просмотреть прайс лист на указанную дату";
    JLabel dataLabel=new JLabel("Дата");
    private JTextField dataField = new JTextField("дата");
    private DefaultTableModel model = new DefaultTableModel(8, 1);



    public MainPanel(ControlListBuros controlListBuros, ControlListTours controlListTours, ControlListHotels controlListHotels){
        super(WINDOW);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,500);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton buttonOne = new JButton(BUTTON_ONE);
        JButton buttonAllOffices = new JButton(BUTTON_ALL_OFFICES);
        JButton buttonAllTours = new JButton(BUTTON_ALL_TOURS);
        JButton buttonAllHotels = new JButton(BUTTON_ALL_HOTELS);
        JButton buttonPriceList = new JButton(BUTTON_PRICE_LIST);


        mainPanel.add(buttonOne);
        mainPanel.add(buttonAllOffices);
        mainPanel.add(buttonAllTours);
        mainPanel.add(buttonAllHotels);
        mainPanel.add(buttonPriceList);
        mainPanel.add(dataLabel);
        mainPanel.add(dataField);

        Object[] columnsHeader = new String[]{"Прайс лист"};
        model.setColumnIdentifiers(columnsHeader);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(500);




        buttonOne.addActionListener(new ButtonOneActionListener());
        buttonAllOffices.addActionListener(new ButtonAllOfficesActionListener());
        buttonAllTours.addActionListener(new ButtonAllToursActionListener());
        buttonAllHotels.addActionListener(new ButtonAllHotelsActionListener());
        buttonPriceList.addActionListener(new ButtonPriceListActionListener());


        this.controlListBuros=controlListBuros;
        this.controlListTours=controlListTours;
        this.controlListHotels=controlListHotels;
    }

    class ButtonPriceListActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one)
        {
            model.setRowCount(0);
         String data=dataField.getText().toString();
         List<String> tours=controlListTours.getToursOnDate(data);
         for (int i=0;i<tours.size();i++){
             Object[] row = new Object[]{
                     tours.get(i),
             };
             model.addRow(row);
         }
            model.fireTableDataChanged();
        }
    }

    class ButtonAllToursActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one)
        {
            AllToursPanel allToursPanel=new AllToursPanel(controlListTours, MainPanel.this);
        }
    }

    class ButtonAllHotelsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one)
        {
            HotelPanel hotelPanel=new HotelPanel(controlListHotels, MainPanel.this);
        }
    }



    class ButtonOneActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one)
        {
            BuroPanel buroPanel=new BuroPanel(controlListBuros, MainPanel.this);
        }
    }

    class ButtonAllOfficesActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one)
        {
            OfficePanel officePanel=new OfficePanel(controlListBuros, MainPanel.this);
        }
    }


}
