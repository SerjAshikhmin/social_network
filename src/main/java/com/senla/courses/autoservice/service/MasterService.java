package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.comparators.master.MasterByBusyComparator;
import com.senla.courses.autoservice.service.comparators.master.MasterByNameComparator;
import com.senla.courses.autoservice.service.interfaces.IMasterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MasterService implements IMasterService {

    private IMasterDao masterDAO;

    public MasterService (IMasterDao masterDAO) {
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
    public List<Master> getAllMastersSorted(String sortBy) {
        List<Master> allMasters = masterDAO.getAllMasters();

        Comparator masterComparator = getMasterComparator(sortBy);
        if (masterComparator != null) {
            allMasters.sort(masterComparator);
        }
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

    private Comparator getMasterComparator(String sortBy) {
        Comparator masterComparator = null;
        switch (sortBy) {
            case "byName":
                masterComparator = new MasterByNameComparator();
                break;
            case "byBusy":
                masterComparator = new MasterByBusyComparator();
                break;
        }
        return masterComparator;
    }

}
