package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;

import java.util.List;

public class GarageController {

    private IGarageService garageService;

    public GarageController(IGarageService garageService) {
        this.garageService = garageService;
    }

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
}
