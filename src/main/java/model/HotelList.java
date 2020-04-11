package model;

import dao.HotelDao;
import dao.TourDao;

import java.util.List;

public class HotelList {
    public HotelList(){}

    public List<Hotel> getHotelList(){
        return HotelDao.getInstance().getAll();
    }

    public  List<String> getHotelFiveStarsList(){
        return HotelDao.getInstance().getFiveStarsHotels();
    }
    public void add(Hotel hotel){
        HotelDao.getInstance().add(hotel);
    }

    public  void delete(int id){
        HotelDao.getInstance().delete(id);
    }

    public  void edit(Hotel hotel, int id){
        HotelDao.getInstance().edit(hotel,id);
    }
}
