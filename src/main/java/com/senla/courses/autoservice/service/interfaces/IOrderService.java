package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    boolean addOrder(Order order);
    boolean removeOrder(Order order);
    Order updateOrder(Order order);
    void cancelOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getAllOrdersSorted(String sortBy);
    List<Order> getAllOrdersInProgress(String sortBy);
    LocalDateTime getNearestFreeDate();
    List<Master> getMastersByOrder(Order order);
    List<Order> getOrdersByPeriod(LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy);
    void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime);
    void shiftEndTimeOrders(int hours, int minutes);

}
