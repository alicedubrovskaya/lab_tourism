package controller;

import model.Hotel;
import model.HotelList;
import model.Tour;

import java.util.List;

public class ControlListHotels {
    private HotelList hotelList;
    public ControlListHotels(HotelList hotelList){
        this.hotelList =hotelList;
    }

    public List<Hotel> getHotelList() {
        return hotelList.getHotelList();
    }

    public List<String> getHotelFiveStarsList(){
        return hotelList.getHotelFiveStarsList();
    }

    public void addHotel(Hotel hotel){
        hotelList.add(hotel);
    }

    public  void deleteHotel(int id){
        hotelList.delete(id);
    }

    public void editHotel(Hotel hotel, int id){
        hotelList.edit(hotel,id);
    }
}
