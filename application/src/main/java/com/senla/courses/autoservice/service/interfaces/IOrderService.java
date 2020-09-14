package com.senla.courses.autoservice.service.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface IOrderService {

    int addOrder(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate,
                     String kindOfWork, int cost, int garageId, int garagePlaceId, String masterName, OrderStatus orderStatus);
    int removeOrder(int id);
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
    int importOrder(String fileName);
    boolean exportOrder(int id, String fileName);
    List<String> toList(Order order);
    void saveState();
    void loadState();
}
