package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.List;

public interface IGarageService {

    boolean addGarage(int id, String address);
    boolean removeGarage(int garageId);
    List<Garage> getAllGarages();
    boolean addGaragePlace(int garageId, int garagePlaceId, String type, int area);
    boolean removeGaragePlace(int garageId, int garagePlaceId);
    List<GaragePlace> getAllFreePlaces();
    int getFreePlacesCountInFuture();
    GaragePlace findGaragePlaceById(int id);
}
