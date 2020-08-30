package com.senla.courses.autoservice.dao.jdbcdao;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.dao.dbqueries.DbQueries;
import com.senla.courses.autoservice.dao.interfaces.IJdbcDao;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderJdbcDao implements IJdbcDao<Order> {

    @InjectByType
    private GaragePlaceJdbcDao garagePlaceJdbcDao;
    @InjectByType
    private MasterJdbcDao masterJdbcDao;
    private Connection connection;

    public OrderJdbcDao() {
        connection = DbJdbcConnector.getConnection();
    }

    @Override
    public int insert(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.INSERT_ORDER_QUERY);
        setPreparedFromOrder(order, preparedStatement);
        preparedStatement.executeUpdate();
        for (Master master : order.getMasters()) {
            masterJdbcDao.update(master);
        }
        return 1;
    }

    @Override
    public int delete(Order order) throws SQLException {
        List<Master> masters = order.getMasters();
        for (Master master : masters) {
            master.setOrderId(0);
            master.setBusy(false);
            masterJdbcDao.update(master);
        }
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.DELETE_ORDER_QUERY);
        preparedStatement.setInt(1, order.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_ORDER_QUERY);
        setPreparedFromOrder(order, preparedStatement);
        preparedStatement.executeUpdate();
        for (Master master : order.getMasters()) {
            masterJdbcDao.update(master);
        }
        return 1;
    }

    @Override
    public Order findById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ORDER_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return setOrderFromResultSet(resultSet);
    }

    @Override
    public List<Order> findAll() throws SQLException{
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_ORDERS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            orders.add(setOrderFromResultSet(resultSet));
        }
        return orders;
    }

    private PreparedStatement setPreparedFromOrder (Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getSubmissionDate().toString());
        String startDate = null;
        if (order.getStartDate() != null) {
            startDate = order.getStartDate().toString();
        }
        preparedStatement.setString(2, startDate);
        String endDate = null;
        if (order.getEndDate() != null) {
            endDate = order.getEndDate().toString();
        }
        preparedStatement.setString(3, endDate);
        preparedStatement.setString(4, order.getKindOfWork());
        preparedStatement.setInt(5, order.getCost());
        preparedStatement.setString(6, order.getStatus().name());
        preparedStatement.setInt(7, order.getGaragePlace().getId());
        preparedStatement.setInt(8, order.getGaragePlace().getGarageId());
        preparedStatement.setInt(9, order.getId());

        return preparedStatement;
    }

    private Order setOrderFromResultSet (ResultSet resultSet) throws SQLException {
        GaragePlace garagePlace = garagePlaceJdbcDao.findGaragePlaceById(resultSet.getInt("garagePlaceGarageId"), resultSet.getInt("garagePlaceId"));
        List<Master> masters = masterJdbcDao.findMastersByOrder(resultSet.getInt("id"));
        Order order = new Order(resultSet.getInt("id"),
                                LocalDateTime.parse(resultSet.getString("submissionDate")),
                                LocalDateTime.parse(resultSet.getString("startDate")),
                                LocalDateTime.parse(resultSet.getString("endDate")),
                                resultSet.getString("kindOfWork"),
                                resultSet.getInt("cost"),
                                garagePlace,
                                masters,
                                OrderStatus.valueOf(resultSet.getString("status"))
                            );
        return order;
    }

}
