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

    private IMasterDao masterDao;

    public MasterService (IMasterDao masterDAO) {
        this.masterDao = masterDAO;
    }

    @Override
    public boolean addMaster(int id, String name, int category) {
        Master master = new Master (id, name, category);
        return masterDao.addMaster(master);
    }

    @Override
    public boolean removeMaster(String name) {
        return masterDao.removeMaster(findMasterByName(name));
    }

    @Override
    public List<Master> getAllMasters() {
        return masterDao.getAllMasters();
    }

    @Override
    public List<Master> getAllMastersSorted(String sortBy) {
        List<Master> allMastersSorted = new ArrayList<>();
        allMastersSorted.addAll(masterDao.getAllMasters());

        Comparator masterComparator = getMasterComparator(sortBy);
        if (masterComparator != null) {
            allMastersSorted.sort(masterComparator);
        }
        return allMastersSorted;
    }

    @Override
    public List<Master> getAllFreeMasters() {
        return masterDao.getAllFreeMasters();
    }

    @Override
    public Order getCurrentOrder(String name) {
        return masterDao.getCurrentOrder(findMasterByName(name));
    }

    @Override
    public Master findMasterByName(String name) {
        for (Master master : getAllMasters()) {
            if (master.getName().equals(name)) {
                return master;
            }
        }
        return null;
    }

    private Comparator getMasterComparator(String sortBy) {
        Comparator masterComparator = null;
        switch (sortBy) {
            case "byName":
                masterComparator = MasterByNameComparator.getInstance();
                break;
            case "byBusy":
                masterComparator = MasterByBusyComparator.getInstance();
                break;
        }
        return masterComparator;
    }

}
