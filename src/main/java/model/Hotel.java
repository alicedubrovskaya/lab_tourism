package model;

public class Hotel {
    private String name;
    private int stars;
    private String countryLocation;
    private int id;

    public  Hotel(String name, int stars, String countryLocation, int id){
        this.name=name;
        this.stars=stars;
        this.countryLocation=countryLocation;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public int getId() {
        return id;
    }
}
