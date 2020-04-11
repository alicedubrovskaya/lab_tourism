package model;

import dao.OfficeDao;
import dao.TourDao;

import java.util.List;

public class TourList {
    public TourList(){}

    public List<Tour> getTourList(){
        return TourDao.getInstance().getAll();
    }

    public  List<String> getToursOnDate(String date){
        return TourDao.getInstance().getToursOnDate(date);
    }
    public void deleteTour(int id){
        TourDao.getInstance().delete(id);
    }

    public void addTour(Tour tour){
        TourDao.getInstance().add(tour);
    }

    public Tour getTour(int id){
        return TourDao.getInstance().getTour(id);
    }



    public void editTour(Tour tour, int id){
        TourDao.getInstance().edit(tour,id);
    }
}
