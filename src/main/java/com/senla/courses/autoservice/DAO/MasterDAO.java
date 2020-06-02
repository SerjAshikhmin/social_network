package com.senla.courses.autoservice.DAO;

import com.senla.courses.autoservice.DAO.interfaces.IMasterDAO;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MasterDAO implements IMasterDAO {

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
        for (Master master : masters) {
            if (master.getId() == id) {
                return master;
            }
        }
        return null;
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

    private Master updateMasterFields(Master master, Master daoMaster) {
        daoMaster.setCategory(master.getCategory());
        daoMaster.setBusy(master.isBusy());
        return daoMaster;
    }
}
