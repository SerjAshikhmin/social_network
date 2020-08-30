package com.senla.courses.autoservice.dao.jdbcdao;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.dao.dbqueries.DbQueries;
import com.senla.courses.autoservice.dao.interfaces.IJdbcDao;
import com.senla.courses.autoservice.model.Master;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MasterJdbcDao implements IJdbcDao<Master> {

    private Connection connection;

    public MasterJdbcDao() {
        connection = DbJdbcConnector.getConnection();
    }

    @Override
    public int insert(Master master) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.INSERT_MASTER_QUERY);
        setPreparedStatementFromMaster(master, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Master master) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.DELETE_MASTER_QUERY);
        preparedStatement.setInt(1, master.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Master master) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_MASTER_QUERY);
        setPreparedStatementFromMaster(master, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    @Override
    public Master findById(int id) throws SQLException {
        Master master;
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_MASTER_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        master = setMasterFromResultSet(resultSet);
        return master;
    }

    @Override
    public List<Master> findAll() throws SQLException {
        List<Master> masters = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_MASTERS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            masters.add(setMasterFromResultSet(resultSet));
        }
        return masters;
    }

    public List<Master> findMastersByOrder(int orderId) throws SQLException {
        List<Master> masters = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_MASTERS_BY_ORDER_QUERY);
        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            masters.add(setMasterFromResultSet(resultSet));
        }
        return masters;
    }

    private PreparedStatement setPreparedStatementFromMaster(Master master, PreparedStatement preparedStatement) throws SQLException {
        int busy = 0;
        if (master.isBusy()) {
            busy = 1;
        }
        if (master.getOrderId() != 0) {
            preparedStatement.setInt(4, master.getOrderId());
        } else {
            preparedStatement.setNull(4, 4);
        }
        preparedStatement.setString(1, master.getName());
        preparedStatement.setInt(2, master.getCategory());
        preparedStatement.setInt(3, busy);
        preparedStatement.setInt(5, master.getId());
        return preparedStatement;
    }

    private Master setMasterFromResultSet (ResultSet resultSet) throws SQLException {
        boolean busy = false;
        if (resultSet.getInt("busy") != 0) {
            busy = true;
        }
        Master master = new Master(resultSet.getInt("id"),
                          resultSet.getString("name"),
                          resultSet.getInt("category"),
                          busy,
                          resultSet.getInt("orderid"));
        return master;
    }
}
