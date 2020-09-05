package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Garage;

import javax.persistence.PersistenceException;
import java.util.List;

@Singleton
public interface IGarageDao {

    int addGarage(Garage garage) throws PersistenceException;
    int removeGarage(Garage garage) throws PersistenceException;
    Garage getGarageById(int id) throws PersistenceException;
    List<Garage> getAllGarages() throws PersistenceException;
    void setAllGarages(List<Garage> allGarages);
    int updateGarage(Garage garage) throws PersistenceException;

}
