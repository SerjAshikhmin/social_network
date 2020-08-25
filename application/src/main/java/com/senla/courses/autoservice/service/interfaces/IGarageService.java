package com.senla.courses.autoservice.service.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.util.List;

@Singleton
public interface IGarageService {

    int addGarage(int id, String address);
    int removeGarage(int garageId);
    List<Garage> getAllGarages();
    int addGaragePlace(int garageId, int garagePlaceId, String type, int area);
    int removeGaragePlace(int garageId, int garagePlaceId);
    List<GaragePlace> getAllFreePlaces();
    int getFreePlacesCountInFuture();
    GaragePlace findGaragePlaceById(int garageId, int garagePlaceId);
    Garage findGarageById(int id);
    int importGarage(String fileName);
    boolean exportGarage(int id, String fileName);
    int importGaragePlace(String fileName);
    boolean exportGaragePlace(int garageId, int garagePlaceId, String fileName);
    List<String> garageToList(Garage garage);
    List<String> garagePlaceToList(GaragePlace garagePlace);
    void saveState();
    void loadState();
}
