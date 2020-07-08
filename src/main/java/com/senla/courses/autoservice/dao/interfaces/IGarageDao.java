package com.senla.courses.autoservice.dao.interfaces;

import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.List;

public interface IGarageDao {

    boolean addGarage(Garage garage);
    boolean removeGarage(Garage garage);
    Garage getGarageById(int id);
    GaragePlace getGaragePlaceById(int garageId, int garagePlaceId);
    List<Garage> getAllGarages();
    void setAllGarages(List<Garage> allGarages);
    Garage updateGarage(Garage garage);
    GaragePlace updateGaragePlace(GaragePlace garagePlace);
    boolean addGaragePlace(GaragePlace garagePlace);
    boolean removeGaragePlace(Garage garage, GaragePlace garagePlace);

}
