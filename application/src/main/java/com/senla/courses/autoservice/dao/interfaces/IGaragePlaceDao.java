package com.senla.courses.autoservice.dao.interfaces;

import com.senla.courses.autoservice.model.GaragePlace;

import javax.persistence.PersistenceException;

public interface IGaragePlaceDao {

    int addGaragePlace(GaragePlace garagePlace) throws PersistenceException;
    int removeGaragePlace(GaragePlace garagePlace) throws PersistenceException;
    int updateGaragePlace(GaragePlace garagePlace) throws PersistenceException;
    GaragePlace getGaragePlaceById(int garageId, int garagePlaceId) throws PersistenceException;
}
