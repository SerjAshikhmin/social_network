package com.senla.courses.autoservice.controller;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.interfaces.IMasterService;

import java.util.List;

@Singleton
public class MasterController {

    @InjectByType
    private IMasterService masterService;

    public int addMaster(int id, String name, int category) {
        return masterService.addMaster(id, name, category);
    }

    public int removeMaster(String name) {
        return masterService.removeMaster(name);
    }

    public List<Master> getAllMastersSorted(String sortBy) {
        return masterService.getAllMastersSorted(sortBy);
    }

    public Order getCurrentOrder(String name) {
        return masterService.getCurrentOrder(name);
    }

    public int importMaster(String fileName) {
        return masterService.importMaster(fileName);
    }

    public boolean exportMaster(int id, String fileName) {
        return masterService.exportMaster(id, fileName);
    }

    public void saveState() {
        masterService.saveState();
    }

    public void loadState() {
        masterService.loadState();
    }
}
