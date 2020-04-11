package model;

import java.util.List;

public class Tour {
    private String typeOfTour;
    private int duration;
    private List<String> countriesVisit;
    private String transport;
    private String departure;
    private  int id;

    public Tour(String typeOfTour, int duration,  String transport, String departure, int id, List<String> countriesVisit){
        this.typeOfTour=typeOfTour;
        this.duration=duration;
        this.countriesVisit=countriesVisit;
        this.transport=transport;
        this.departure=departure;
        this.id=id;
        this.countriesVisit=countriesVisit;
    }

    public String getTypeOfTour() {
        return typeOfTour;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getCountriesVisit() {
        return countriesVisit;
    }

    public String getTransport() {
        return transport;
    }

    public String getDeparture() {
        return departure;
    }

    public int getId() {
        return id;
    }
}
