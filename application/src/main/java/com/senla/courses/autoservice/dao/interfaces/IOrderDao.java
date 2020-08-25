package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Singleton
public interface IOrderDao {

    int addOrder(Order order) throws SQLException;
    int removeOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    void setAllOrders(List<Order> allOrders);
    int updateOrder(Order order) throws SQLException;
    void cancelOrder(Order order) throws SQLException;
    void closeOrder(Order order) throws SQLException;
    void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime) throws SQLException;
    List<Master> getMastersByOrder(Order order) throws OrderNotFoundException;
    void updateAllOrders(List<Order> orders);
    List<Order> getAllOrdersInProgress(Comparator orderComparator) throws SQLException;

}
