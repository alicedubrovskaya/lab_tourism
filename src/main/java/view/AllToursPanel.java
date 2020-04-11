package view;

import controller.ControlListBuros;
import controller.ControlListTours;
import model.Buro;
import model.Tour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AllToursPanel {
    private MainPanel main;
    private ControlListTours controlListTours;
    private List<Tour> tours;
    private static final String BUTTON_ADD_TOUR = "Добавить тур";
    private static final String BUTTON_DELETE = "Удалить тур";
    private static final String BUTTON_EDIT = "Редактировать тур";
    private static final String BUTTON_SHOW = "Отобразить информацию о туре";




    private JPanel tourPanel=new JPanel();
    private DefaultTableModel model = new DefaultTableModel(8,5);
    private JButton buttonAdd = new JButton(BUTTON_ADD_TOUR);
    private JButton buttonDelete = new JButton(BUTTON_DELETE);
    private JButton buttonEdit = new JButton(BUTTON_EDIT);
    private JButton buttonShow = new JButton(BUTTON_SHOW);




    private static final String SET_TITLE = "";


    AllToursPanel(ControlListTours controlListTours, MainPanel main){
        this.main=main;
        this.controlListTours=controlListTours;

        Object[] columnsHeader = new String[]{"id","Тип тура","Продолжительность","Страны посещения","Транспорт","Пункт отправления"};
        model.setColumnIdentifiers(columnsHeader);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        tourPanel.add(scrollPane);
        tourPanel.add(buttonAdd);
        tourPanel.add(buttonDelete);
        tourPanel.add(buttonEdit);
        tourPanel.add(buttonShow);

        scrollPane.setPreferredSize(new Dimension(500,200));

        updateTable();

        JDialog dialog = new JDialog();
        dialog.add(tourPanel);
        dialog.setTitle(SET_TITLE);
        dialog.setResizable(false);
        dialog.setSize(600, 600);
        dialog.setVisible(true);

        buttonAdd.addActionListener(new ButtonAddActionListener());
        buttonDelete.addActionListener(new ButtonDeleteActionListener());
        buttonEdit.addActionListener(new ButtonEditActionListener());
        buttonShow.addActionListener(new ButtonShowActionListener());


    }

    class ButtonAddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            TourPanelAdd tourPanelAdd=new TourPanelAdd(controlListTours,AllToursPanel.this);
        }
    }

    class ButtonDeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            TourPanelDelete tourPanelDelete=new TourPanelDelete(controlListTours, AllToursPanel.this);
        }
    }

    class ButtonEditActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            TourPanelEdit tourPanelEdit=new TourPanelEdit(controlListTours,AllToursPanel.this);
    }
    }


    class ButtonShowActionListener implements ActionListener {
        public void actionPerformed(ActionEvent one) {
            TourPanelShow tourPanelShow=new TourPanelShow(controlListTours, AllToursPanel.this);
        }
    }

    void updateTable(){
        Tour tour;
        tours=controlListTours.getTourList();

        int countOfTours=tours.size();
        model.setRowCount(0);

        for (int i=0;i<countOfTours;i++){
            tour=tours.get(i);
            Object[] row = new Object[]{
                    tour.getId(),
                    tour.getTypeOfTour(),
                    tour.getDuration(),
                    convert(tour.getCountriesVisit()),//????
                    tour.getTransport(),
                    tour.getDeparture(),
            };
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }

    public String convert(List<String> string){
        StringBuilder result= new StringBuilder();
        for(String s: string){
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
