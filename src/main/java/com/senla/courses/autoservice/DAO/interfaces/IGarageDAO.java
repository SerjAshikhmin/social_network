package com.senla.courses.autoservice.DAO.interfaces;

import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.List;

public interface IGarageDAO {

    boolean addGarage(Garage garage);
    boolean removeGarage(Garage garage);
    Garage getGarageById(int id);
    List<Garage> getAllGarages();
    Garage updateGarage(Garage garage);
    boolean addGaragePlace(Garage garage, GaragePlace garagePlace);
    boolean removeGaragePlace(Garage garage, GaragePlace garagePlace);

}
