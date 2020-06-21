package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.interfaces.IMasterService;

import java.util.List;

public class MasterController {

    private IMasterService masterService;

    public MasterController(IMasterService masterService) {
        this.masterService = masterService;
    }

    public boolean addMaster(int id, String name, int category) {
        return masterService.addMaster(id, name, category);
    }

    public boolean removeMaster(String name) {
        return masterService.removeMaster(name);
    }

    public List<Master> getAllMastersSorted(String sortBy) {
        return masterService.getAllMastersSorted(sortBy);
    }

    public Order getCurrentOrder(String name) {
        return masterService.getCurrentOrder(name);
    }

    public boolean importMaster(String fileName) {
        return masterService.importMaster(fileName);
    }

    public boolean exportMaster(int id, String fileName) {
        return masterService.exportMaster(id, fileName);
    }
}
