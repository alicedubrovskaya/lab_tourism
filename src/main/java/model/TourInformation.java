package model;

public class TourInformation {
    Tour tour;
    private int count;
    private String dateOfDeparture;
    private int cost;

    public TourInformation(int count, String dateOfDeparture, int cost, Tour tour){
        this.count=count;
        this.dateOfDeparture=dateOfDeparture;
        this.cost=cost;
        this.tour=tour;
    }


    public int getCount() {
        return count;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public int getCost() {
        return cost;
    }

    public Tour getTour(){
        return tour;
    }
}
