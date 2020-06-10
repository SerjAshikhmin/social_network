package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;

import java.util.ArrayList;
import java.util.List;

public class GarageService implements IGarageService {

    private IGarageDao garageDao;

    public GarageService(IGarageDao garageDAO) {
        this.garageDao = garageDAO;
    }

    @Override
    public boolean addGarage(Garage garage) {
        return garageDao.addGarage(garage);
    }

    @Override
    public boolean removeGarage(Garage garage) {
        return garageDao.removeGarage(garage);
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageDao.getAllGarages();
    }

    @Override
    public boolean addGaragePlace(Garage garage, GaragePlace garagePlace) {
        return garageDao.addGaragePlace(garage, garagePlace);
    }

    @Override
    public boolean removeGaragePlace(Garage garage, GaragePlace garagePlace) {
        return garageDao.removeGaragePlace(garage, garagePlace);
    }

    @Override
    public List<GaragePlace> getAllFreePlaces() {
        List<GaragePlace> freePlaces = new ArrayList<>();
        garageDao.getAllGarages().stream()
                .forEach(garage -> garage.getGaragePlaces()
                        .stream().filter(garagePlace -> !garagePlace.isBusy())
                        .forEach(garagePlace -> freePlaces.add(garagePlace)));

        return freePlaces;
    }

}
