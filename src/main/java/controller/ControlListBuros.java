package controller;

import dao.BuroDao;
import dao.OfficeDao;
import model.Buro;
import model.BuroList;
import model.Office;

import java.util.List;

public class ControlListBuros {
    private BuroList buroList;

    public ControlListBuros(BuroList buroList) {
        this.buroList = buroList;
    }

    public  void addOffice(Office office){
        buroList.addOffice(office);
    }

    public void deleteOffice(String address){
        buroList.deleteOffice(address);
    }
    public  void editOffice(Office office, String address){buroList.editOffice(office, address);}

    public List<Buro> getBuros() {
        return buroList.getBurosList();
    }

    public List<Office> getAllOffices() {
        return buroList.getOfficeList();
    }
}
