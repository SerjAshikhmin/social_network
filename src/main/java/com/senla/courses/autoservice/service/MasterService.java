package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.DAO.interfaces.IMasterDAO;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.interfaces.IMasterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MasterService implements IMasterService {

    private IMasterDAO masterDAO;

    public MasterService (IMasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public boolean addMaster(Master master) {
        return masterDAO.addMaster(master);
    }

    @Override
    public boolean removeMaster(Master master) {
        return masterDAO.removeMaster(master);
    }

    @Override
    public List<Master> getAllMasters() {
        return masterDAO.getAllMasters();
    }

    @Override
    public List<Master> getAllMasters(Comparator masterComparator) {
        List<Master> allMasters = masterDAO.getAllMasters();
        allMasters.sort(masterComparator);
        return allMasters;
    }

    @Override
    public List<Master> getAllFreeMasters() {
        List<Master> freeMasters = new ArrayList<>();
        for (Master master : masterDAO.getAllMasters()) {
            if (!master.isBusy()) {
                freeMasters.add(master);
            }
        }
        return freeMasters;
    }

    @Override
    public Order getCurrentOrder(Master master) {
        return masterDAO.getCurrentOrder(master);
    }

}
