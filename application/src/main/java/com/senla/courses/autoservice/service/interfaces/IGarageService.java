package com.senla.courses.autoservice.service.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.List;

@Singleton
public interface IGarageService {

    boolean addGarage(int id, String address);
    boolean removeGarage(int garageId);
    List<Garage> getAllGarages();
    boolean addGaragePlace(int garageId, int garagePlaceId, String type, int area);
    boolean removeGaragePlace(int garageId, int garagePlaceId);
    List<GaragePlace> getAllFreePlaces();
    int getFreePlacesCountInFuture();
    GaragePlace findGaragePlaceById(int garageId, int garagePlaceId);
    Garage findGarageById(int id);
    boolean importGarage(String fileName);
    boolean exportGarage(int id, String fileName);
    boolean importGaragePlace(String fileName);
    boolean exportGaragePlace(int garageId, int garagePlaceId, String fileName);
    List<String> garageToList(Garage garage);
    List<String> garagePlaceToList(GaragePlace garagePlace);
    void saveState();
    void loadState();
}
