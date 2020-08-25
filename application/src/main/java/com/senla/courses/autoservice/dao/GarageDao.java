package com.senla.courses.autoservice.dao;

import com.lib.dicontainer.annotations.InjectByType;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.jdbcdao.GarageJdbcDao;
import com.senla.courses.autoservice.dao.jdbcdao.GaragePlaceJdbcDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.sql.SQLException;
import java.util.List;

public class GarageDao implements IGarageDao {

    @InjectByType
    GarageJdbcDao garageJdbcDao;
    @InjectByType
    GaragePlaceJdbcDao garagePlaceJdbcDao;

    @Override
    public int addGarage(Garage garage) throws SQLException {
        return garageJdbcDao.insert(garage);
    }

    @Override
    public int removeGarage(Garage garage) throws SQLException {
        return garageJdbcDao.delete(garage);
    }

    @Override
    public Garage getGarageById(int id) throws SQLException {
        return garageJdbcDao.findById(id);
    }

    @Override
    public GaragePlace getGaragePlaceById(int garageId, int garagePlaceId) throws SQLException {
        return garagePlaceJdbcDao.findGaragePlaceById(garageId, garagePlaceId);
    }

    @Override
    public List<Garage> getAllGarages() throws SQLException {
        return garageJdbcDao.findAll();
    }

    @Override
    public void setAllGarages(List<Garage> allGarages) {
        //this.garages = allGarages;
    }

    @Override
    public int updateGarage(Garage garage) throws SQLException {
        return garageJdbcDao.update(garage);
    }

    @Override
    public int updateGaragePlace(GaragePlace garagePlace) throws SQLException {
        return garagePlaceJdbcDao.update(garagePlace);
    }

    @Override
    public int addGaragePlace(GaragePlace garagePlace) throws SQLException {
        return garagePlaceJdbcDao.insert(garagePlace);
    }

    @Override
    public int removeGaragePlace(GaragePlace garagePlace) throws SQLException {
        return garagePlaceJdbcDao.delete(garagePlace);
    }

}
