package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;

import java.util.ArrayList;
import java.util.List;

public class GarageService implements IGarageService {

    private IGarageDao garageDao;
    private IMasterService masterService;

    public GarageService(IGarageDao garageDAO, IMasterService masterService) {
        this.garageDao = garageDAO;
        this.masterService = masterService;
    }

    @Override
    public boolean addGarage(int id, String address) {
        Garage garage = new Garage(id, address, new ArrayList<>());
        return garageDao.addGarage(garage);
    }

    @Override
    public boolean removeGarage(int garageId) {
        return garageDao.removeGarage(findGarageById(garageId));
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageDao.getAllGarages();
    }

    @Override
    public boolean addGaragePlace(int garageId, int garagePlaceId, String type, int area) {
        GaragePlace garagePlace = new GaragePlace(garagePlaceId, type, area);
        return garageDao.addGaragePlace(findGarageById(garageId), garagePlace);
    }

    @Override
    public boolean removeGaragePlace(int garageId, int garagePlaceId) {
        return garageDao.removeGaragePlace(findGarageById(garageId), findGaragePlaceById(garagePlaceId));
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

    @Override
    public int getFreePlacesCountInFuture() {
        return Math.min(getAllFreePlaces().size(), masterService.getAllFreeMasters().size());
    }

    @Override
    public GaragePlace findGaragePlaceById(int id) {
        for (GaragePlace garagePlace : getAllFreePlaces()) {
            if (garagePlace.getId() == id) {
                return garagePlace;
            }
        }
        return null;
    }

    private Garage findGarageById(int id) {
        for (Garage garage : getAllGarages()) {
            if (garage.getId() == id) {
                return garage;
            }
        }
        return null;
    }

}
