package model;

import dao.BuroDao;
import dao.OfficeDao;

import java.util.ArrayList;
import java.util.List;

public class Buro {
    private String fullName;
    private String number;
    private String name;
    private int id;

    public Buro() {
    }

    public  Buro(String fullName, String number, String name, int id){
        this.fullName=fullName;
        this.name=name;
        this.number=number;
        this.id=id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public int getId(){return id;}
}
