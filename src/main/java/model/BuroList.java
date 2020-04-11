package model;

import dao.BuroDao;
import dao.OfficeDao;

import java.util.ArrayList;
import java.util.List;

public class BuroList {

    public BuroList() {
    }

    public void addOffice(Office office){
        OfficeDao.getInstance().add(office);
    }

    public  void deleteOffice(String address){
        OfficeDao.getInstance().delete(address);
    }
    public  void editOffice(Office office, String address){
        OfficeDao.getInstance().edit(office, address);
    }

    public List<Buro> getBurosList() {
        return BuroDao.getInstance().getAll();
    }

    public  List<Office> getOfficeList(){
        return  OfficeDao.getInstance().getAll();
    }
}
