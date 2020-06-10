package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.ArrayList;
import java.util.List;

public interface IGarageService {

    boolean addGarage(Garage garage);
    boolean removeGarage(Garage garage);
    List<Garage> getAllGarages();
    boolean addGaragePlace(Garage garage, GaragePlace garagePlace);
    boolean removeGaragePlace(Garage garage, GaragePlace garagePlace);
    List<GaragePlace> getAllFreePlaces();
}
