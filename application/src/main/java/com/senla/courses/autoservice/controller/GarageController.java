package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GarageController {

    @Autowired
    private IGarageService garageService;

    public int addGarage(int id, String address) {
        return garageService.addGarage(id, address);
    }

    public int removeGarage(int id) {
        return garageService.removeGarage(id);
    }

    public int addGaragePlace(int garageId, int garagePlaceId, String type, int area) {
        return garageService.addGaragePlace(garageId, garagePlaceId, type, area);
    }

    public int removeGaragePlace(int garageId, int garagePlaceId) {
        return garageService.removeGaragePlace(garageId, garagePlaceId);
    }

    public List<GaragePlace> getAllFreePlaces() {
        return garageService.getAllFreePlaces();
    }

    public int getFreePlacesCountInFuture() {
        return garageService.getFreePlacesCountInFuture();
    }

    public int importGarage(String fileName) {
        return garageService.importGarage(fileName);
    }

    public boolean exportGarage(int id, String fileName) {
        return garageService.exportGarage(id, fileName);
    }

    public int importGaragePlace(String fileName) {
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
