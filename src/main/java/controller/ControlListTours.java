package controller;

import model.Tour;
import model.TourList;

import java.util.List;

public class ControlListTours {
        private TourList tourList;
        public ControlListTours(TourList tourList){
            this.tourList=tourList;
        }

    public List<Tour> getTourList() {
        return tourList.getTourList();
    }

    public List<String> getToursOnDate(String date){
            return tourList.getToursOnDate(date);
    }
    public void deleteTour(String id){
            tourList.deleteTour(Integer.parseInt (id));
    }

    public void addTour(Tour tour){
            tourList.addTour(tour);
    }

    public Tour getTour(int id){
            return tourList.getTour(id);
    }

    public void editTour(Tour tour, int id){
            tourList.editTour(tour,id);
    }
}

