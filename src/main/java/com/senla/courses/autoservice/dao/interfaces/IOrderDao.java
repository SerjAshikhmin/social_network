package com.senla.courses.autoservice.dao.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public interface IOrderDao {

    boolean addOrder(Order order);
    boolean removeOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    void cancelOrder(Order order);
    void updateOrderTime(Order order, GregorianCalendar newStartTime, GregorianCalendar newEndTime);
    List<Master> getMastersByOrder (Order order);
    void updateAllOrders(List<Order> orders);
    List<Order> getAllOrdersInProgress(Comparator orderComparator);

}