package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MasterDao implements IMasterDao {

    private List<Master> masters = new ArrayList<>();

    @Override
    public boolean addMaster(Master master) {
        return this.masters.add(master);
    }

    @Override
    public boolean removeMaster(Master master) {
        return this.masters.remove(master);
    }

    @Override
    public Master getMasterById(int id) {
        return masters.stream().filter(master -> master.getId() == id).findFirst().get();
    }

    @Override
    public List<Master> getAllMasters() {
        return masters;
    }

    @Override
    public Master updateMaster(Master master) {
        Master daoMaster = getMasterById(master.getId());
        return updateMasterFields(master, daoMaster);
    }

    public Order getCurrentOrder(Master master) {
        return master.getCurrentOrder();
    }

    @Override
    public List<Master> getAllFreeMasters() {
        return getAllMasters().stream().filter(master -> !master.isBusy()).collect(Collectors.toList());
    }

    private Master updateMasterFields(Master master, Master daoMaster) {
        daoMaster.setCategory(master.getCategory());
        daoMaster.setBusy(master.isBusy());
        return daoMaster;
    }
}
