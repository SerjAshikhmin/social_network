package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GarageDao implements IGarageDao {

    private List<Garage> garages;

    public GarageDao() {
        this.garages = new ArrayList<>();
    }

    @Override
    public boolean addGarage(Garage garage) {
        return this.garages.add(garage);
    }

    @Override
    public boolean removeGarage(Garage garage) {
        return this.garages.remove(garage);
    }

    @Override
    public Garage getGarageById(int id) {
        try {
            return garages.stream()
                    .filter(garage -> garage.getId() == id)
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public GaragePlace getGaragePlaceById(int garageId, int garagePlaceId) {
        Garage garage = getGarageById(garageId);
        if (garage == null) {
            return null;
        }
        try {
            return garage.getGaragePlaces().stream()
                    .filter(garagePlace -> garagePlace.getId() == garagePlaceId)
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<Garage> getAllGarages() {
        return garages;
    }

    @Override
    public void setAllGarages(List<Garage> allGarages) {
        this.garages = allGarages;
    }

    @Override
    public Garage updateGarage(Garage garage) {
        Garage daoGarage = getGarageById(garage.getId());
        return updateGarageFields(garage, daoGarage);
    }

    @Override
    public GaragePlace updateGaragePlace(GaragePlace garagePlace) {
        GaragePlace daoGaragePlace = getGaragePlaceById(garagePlace.getGarageId(), garagePlace.getId());
        return updateGaragePlaceFields(garagePlace, daoGaragePlace);
    }

    @Override
    public boolean addGaragePlace(GaragePlace garagePlace) {
        Garage daoGarage = getGarageById(garagePlace.getGarageId());
        return daoGarage.getGaragePlaces().add(garagePlace);
    }

    @Override
    public boolean removeGaragePlace(Garage garage, GaragePlace garagePlace) {
        if (garage == null || garagePlace == null) {
            return false;
        }
        Garage daoGarage = getGarageById(garage.getId());
        return daoGarage.getGaragePlaces().remove(garagePlace);
    }

    private Garage updateGarageFields(Garage garage, Garage daoGarage) {
        daoGarage.setAddress(garage.getAddress());
        daoGarage.setGaragePlaces(garage.getGaragePlaces());
        return daoGarage;
    }

    private GaragePlace updateGaragePlaceFields(GaragePlace garagePlace, GaragePlace daoGaragePlace) {
        daoGaragePlace.setType(garagePlace.getType());
        daoGaragePlace.setBusy(garagePlace.isBusy());
        daoGaragePlace.setArea(garagePlace.getArea());
        return daoGaragePlace;
    }

}
