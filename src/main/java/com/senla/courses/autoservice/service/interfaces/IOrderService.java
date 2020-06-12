package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    boolean addOrder(int id, LocalDateTime plannedStartDate, LocalDateTime startDate, LocalDateTime endDate,
                     String kindOfWork, int cost, int garagePlaceId, String masterName, OrderStatus orderStatus);
    boolean removeOrder(int id);
    Order updateOrder(Order order);
    void cancelOrder(int id);
    void closeOrder(int id);
    List<Order> getAllOrders();
    List<Order> getAllOrdersSorted(String sortBy);
    List<Order> getAllOrdersInProgress(String sortBy);
    LocalDateTime getNearestFreeDate();
    List<Master> getMastersByOrder(int id);
    List<Order> getOrdersByPeriod(LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy);
    void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime);
    void shiftEndTimeOrders(int hours, int minutes);
    Order findOrderById(int id);

}
