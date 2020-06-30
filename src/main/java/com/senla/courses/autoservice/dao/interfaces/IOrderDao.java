package com.senla.courses.autoservice.dao.interfaces;

import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface IOrderDao {

    boolean addOrder(Order order);
    boolean removeOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    void cancelOrder(Order order);
    void closeOrder(Order order);
    void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime);
    List<Master> getMastersByOrder(Order order) throws OrderNotFoundException;
    void updateAllOrders(List<Order> orders);
    List<Order> getAllOrdersInProgress(Comparator orderComparator);

}
