package model;

public class Office {
    private String address;
    private String number;
    private String contactPerson;
    private String mail;
    private int id;

    public Office(String address, String number, String contactPerson, String mail, int id){
        this.address=address;
        this.contactPerson=contactPerson;
        this.mail=mail;
        this.number=number;
        this.id=id;
    }


    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getMail() {
        return mail;
    }
    public int getId(){return id;}
}
