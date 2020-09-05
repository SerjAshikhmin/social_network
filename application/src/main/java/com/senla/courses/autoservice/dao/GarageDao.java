package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.jpadao.AbstractJpaDao;
import com.senla.courses.autoservice.model.Garage;

import javax.persistence.PersistenceException;
import java.util.List;

public class GarageDao extends AbstractJpaDao<Garage> implements IGarageDao {

    public GarageDao() {
        super(Garage.class);
    }

    @Override
    public int addGarage(Garage garage) throws PersistenceException {
        return insert(garage);
    }

    @Override
    public int removeGarage(Garage garage) throws PersistenceException {
        return delete(garage);
    }

    @Override
    public Garage getGarageById(int id) throws PersistenceException {
        return findById(id);
    }

    @Override
    public List<Garage> getAllGarages() throws PersistenceException {
        return findAll();
    }

    @Override
    public void setAllGarages(List<Garage> allGarages) {
        //this.garages = allGarages;
    }

    @Override
    public int updateGarage(Garage garage) throws PersistenceException {
        return update(garage);
    }

}
