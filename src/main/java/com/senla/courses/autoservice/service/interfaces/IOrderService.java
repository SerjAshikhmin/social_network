package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public interface IOrderService {

    boolean addOrder(Order order);
    boolean removeOrder(Order order);
    Order updateOrder(Order order);
    void cancelOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getAllOrders(Comparator orderComparator);
    List<Order> getAllOrdersInProgress(Comparator orderComparator);
    GregorianCalendar getNearestFreeDate();
    List<Master> getMastersByOrder (Order order);
    List<Order> getOrdersByPeriod (GregorianCalendar startPeriod, GregorianCalendar endPeriod, Comparator orderComparator);
    void updateOrderTime(Order order, GregorianCalendar newStartTime, GregorianCalendar newEndTime);
    void shiftEndTimeOrders(int hours, int minutes);

}
