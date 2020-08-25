package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.sql.SQLException;
import java.util.List;

@Singleton
public interface IGarageDao {

    int addGarage(Garage garage) throws SQLException;
    int removeGarage(Garage garage) throws SQLException;
    Garage getGarageById(int id) throws SQLException;
    GaragePlace getGaragePlaceById(int garageId, int garagePlaceId) throws SQLException;
    List<Garage> getAllGarages() throws SQLException;
    void setAllGarages(List<Garage> allGarages);
    int updateGarage(Garage garage) throws SQLException;
    int updateGaragePlace(GaragePlace garagePlace) throws SQLException;
    int addGaragePlace(GaragePlace garagePlace) throws SQLException;
    int removeGaragePlace(GaragePlace garagePlace) throws SQLException;

}
