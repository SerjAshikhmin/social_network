package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;

import java.util.ArrayList;
import java.util.List;

public class GarageService implements IGarageService {

    private IGarageDao garageDAO;

    public GarageService(IGarageDao garageDAO) {
        this.garageDAO = garageDAO;
    }

    @Override
    public boolean addGarage(Garage garage) {
        return garageDAO.addGarage(garage);
    }

    @Override
    public boolean removeGarage(Garage garage) {
        return garageDAO.removeGarage(garage);
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageDAO.getAllGarages();
    }

    @Override
    public boolean addGaragePlace(Garage garage, GaragePlace garagePlace) {
        return garageDAO.addGaragePlace(garage, garagePlace);
    }

    @Override
    public boolean removeGaragePlace(Garage garage, GaragePlace garagePlace) {
        return garageDAO.removeGaragePlace(garage, garagePlace);
    }

    @Override
    public List<GaragePlace> getAllFreePlaces() {
        List<GaragePlace> freePlaces = new ArrayList<>();
        for (Garage garage : garageDAO.getAllGarages()) {
            for (GaragePlace garagePlace : garage.getGaragePlaces()) {
                if (!garagePlace.isBusy()) {
                    freePlaces.add(garagePlace);
                }
            }
        }
        return freePlaces;
    }
}
