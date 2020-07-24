package com.senla.courses.autoservice.controller;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;

import java.util.List;

@Singleton
public class GarageController {

    @InjectByType
    private IGarageService garageService;

    public boolean addGarage(int id, String address) {
        return garageService.addGarage(id, address);
    }

    public boolean removeGarage(int id) {
        return garageService.removeGarage(id);
    }

    public boolean addGaragePlace(int garageId, int garagePlaceId, String type, int area) {
        return garageService.addGaragePlace(garageId, garagePlaceId, type, area);
    }

    public boolean removeGaragePlace(int garageId, int garagePlaceId) {
        return garageService.removeGaragePlace(garageId, garagePlaceId);
    }

    public List<GaragePlace> getAllFreePlaces() {
        return garageService.getAllFreePlaces();
    }

    public int getFreePlacesCountInFuture() {
        return garageService.getFreePlacesCountInFuture();
    }

    public boolean importGarage(String fileName) {
        return garageService.importGarage(fileName);
    }

    public boolean exportGarage(int id, String fileName) {
        return garageService.exportGarage(id, fileName);
    }

    public boolean importGaragePlace(String fileName) {
        return garageService.importGaragePlace(fileName);
    }

    public boolean exportGaragePlace(int garageId, int garagePlaceId, String fileName) {
        return garageService.exportGaragePlace(garageId, garagePlaceId, fileName);
    }

    public void saveState() {
        garageService.saveState();
    }

    public void loadState() {
        garageService.loadState();
    }
}
