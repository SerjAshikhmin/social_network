package com.senla.courses.autoservice.DAO;

import com.senla.courses.autoservice.DAO.interfaces.IGarageDAO;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.ArrayList;
import java.util.List;

public class GarageDAO implements IGarageDAO {

    private List<Garage> garages = new ArrayList<>();

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
        for (Garage garage : garages) {
            if (garage.getId() == id) {
                return garage;
            }
        }
        return null;
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
        return garage.getGaragePlaces().add(garagePlace);
    }

    @Override
    public boolean removeGaragePlace(Garage garage, GaragePlace garagePlace) {
        return garage.getGaragePlaces().remove(garagePlace);
    }

    private Garage updateGarageFields(Garage garage, Garage daoGarage) {
        daoGarage.setGaragePlaces(garage.getGaragePlaces());
        return daoGarage;
    }


}
