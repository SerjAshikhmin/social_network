package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.ArrayList;
import java.util.List;

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
        return garages.stream()
                .filter(garage -> garage.getId() == id)
                .findFirst().get();
    }

    @Override
    public List<Garage> getAllGarages() {
        return garages;
    }

    @Override
    public Garage updateGarage(Garage garage) {
        Garage daoGarage = getGarageById(garage.getId());
        return updateGarageFields(garage, daoGarage);
    }

    @Override
    public boolean addGaragePlace(Garage garage, GaragePlace garagePlace) {
        Garage daoGarage = getGarageById(garage.getId());
        return daoGarage.getGaragePlaces().add(garagePlace);
    }

    @Override
    public boolean removeGaragePlace(Garage garage, GaragePlace garagePlace) {
        Garage daoGarage = getGarageById(garage.getId());
        return daoGarage.getGaragePlaces().remove(garagePlace);
    }

    private Garage updateGarageFields(Garage garage, Garage daoGarage) {
        daoGarage.setGaragePlaces(garage.getGaragePlaces());
        return daoGarage;
    }

}
