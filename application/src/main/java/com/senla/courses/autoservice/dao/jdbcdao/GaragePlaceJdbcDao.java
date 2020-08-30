package com.senla.courses.autoservice.dao.jdbcdao;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.dao.dbqueries.DbQueries;
import com.senla.courses.autoservice.dao.interfaces.IJdbcDao;
import com.senla.courses.autoservice.model.GaragePlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GaragePlaceJdbcDao implements IJdbcDao<GaragePlace> {

    private Connection connection;

    public GaragePlaceJdbcDao() {
        connection = DbJdbcConnector.getConnection();
    }

    @Override
    public int insert(GaragePlace garagePlace) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.INSERT_GARAGE_PLACE_QUERY);
        setPreparedStatementFromGaragePlace(garagePlace, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(GaragePlace garagePlace) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.DELETE_GARAGE_PLACE_QUERY);
        preparedStatement.setInt(1, garagePlace.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(GaragePlace garagePlace) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_GARAGE_PLACE_QUERY);
        setPreparedStatementFromGaragePlace(garagePlace, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public GaragePlace findById(int id) {
        return null;
    }

    public GaragePlace findGaragePlaceById(int garageId, int garagePlaceId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_GARAGE_PLACE_QUERY);
        preparedStatement.setInt(1, garagePlaceId);
        preparedStatement.setInt(2, garageId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return setGaragePlaceFromResultSet(resultSet);
    }

    @Override
    public List<GaragePlace> findAll() throws SQLException {
        List<GaragePlace> garagePlaces = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_GARAGE_PLACES_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            garagePlaces.add(setGaragePlaceFromResultSet(resultSet));
        }
        return garagePlaces;
    }

    private PreparedStatement setPreparedStatementFromGaragePlace (GaragePlace garagePlace, PreparedStatement preparedStatement) throws SQLException {
        int busy = 0;
        if (garagePlace.isBusy()) {
            busy = 1;
        }
        preparedStatement.setString(1, garagePlace.getType());
        preparedStatement.setInt(2, garagePlace.getArea());
        preparedStatement.setInt(3, busy);
        preparedStatement.setInt(4, garagePlace.getId());
        preparedStatement.setInt(5, garagePlace.getGarageId());
        return preparedStatement;
    }

    private GaragePlace setGaragePlaceFromResultSet (ResultSet resultSet) throws SQLException {
        boolean busy = false;
        if (resultSet.getInt("busy") != 0) {
            busy = true;
        }
        GaragePlace garagePlace =  new GaragePlace(resultSet.getInt("id"),
                                                   resultSet.getInt("garageId"),
                                                   resultSet.getString("type"),
                                                   resultSet.getInt("area"),
                                                   busy);
        return garagePlace;
    }

}
