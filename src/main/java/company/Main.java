package company;

import controller.ControlListBuros;
import controller.ControlListHotels;
import controller.ControlListTours;
import dao.HotelDao;
import model.BuroList;
import model.HotelList;
import model.TourList;
import view.MainPanel;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BuroList buroList=new BuroList();
        TourList tourList=new TourList();
        HotelList hotelList=new HotelList();

        ControlListBuros controlListBuros=new ControlListBuros(buroList);
        ControlListTours controlListTours=new ControlListTours(tourList);
        ControlListHotels controlListHotels=new ControlListHotels(hotelList);

        MainPanel mainPanel= new MainPanel(controlListBuros, controlListTours, controlListHotels);
        mainPanel.setVisible(true);

    }
}
