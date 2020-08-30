package com.senla.courses.autoservice.dao.jdbcdao;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.dao.dbqueries.DbQueries;
import com.senla.courses.autoservice.dao.interfaces.IJdbcDao;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GarageJdbcDao implements IJdbcDao<Garage> {

    @InjectByType
    private GaragePlaceJdbcDao garagePlaceJdbcDao;
    private Connection connection;

    public GarageJdbcDao() {
        connection = DbJdbcConnector.getConnection();
    }

    @Override
    public int insert(Garage garage) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.INSERT_GARAGE_QUERY);
        setPreparedStatementFromGarage(garage, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Garage garage) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.DELETE_GARAGE_QUERY);
        preparedStatement.setInt(1, garage.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Garage garage) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_GARAGE_QUERY);
        setPreparedStatementFromGarage(garage, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public Garage findById(int id) throws SQLException {
        Garage garage;
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_GARAGE_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        garage = setGarageFromResultSet(resultSet);
        return garage;
    }

    @Override
    public List<Garage> findAll() throws SQLException {
        List<Garage> garages = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_GARAGES_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            garages.add(setGarageFromResultSet(resultSet));
        }
        return garages;
    }

    private List<GaragePlace> findGaragePlacesByGarageId(int garageId) throws SQLException {
        List<GaragePlace> allGaragePlaces = garagePlaceJdbcDao.findAll();
        List<GaragePlace> garagePlacesInCurrentGarage = new ArrayList<>();
        allGaragePlaces.forEach(garagePlace -> {
            if (garagePlace.getGarageId() == garageId) {
                garagePlacesInCurrentGarage.add(garagePlace);
            }
        });
        return garagePlacesInCurrentGarage;
    }

    private PreparedStatement setPreparedStatementFromGarage(Garage garage, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, garage.getAddress());
        preparedStatement.setInt(2, garage.getId());
        return preparedStatement;
    }

    private Garage setGarageFromResultSet (ResultSet resultSet) throws SQLException {
        int garageId = resultSet.getInt("id");
        return new Garage(resultSet.getInt("id"), resultSet.getString("adress"), findGaragePlacesByGarageId(garageId));
    }

}
