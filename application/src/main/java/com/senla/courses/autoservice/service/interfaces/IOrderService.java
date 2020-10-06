package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface IOrderService {

    int addOrder(Order order);
    int removeOrder(int id);
    int cancelOrder(int id);
    int closeOrder(int id);
    List<Order> getAllOrders();
    List<Order> getAllOrdersSorted(String sortBy);
    List<Order> getAllOrdersInProgress(String sortBy);
    LocalDateTime getNearestFreeDate();
    List<Master> getMastersByOrder(int id);
    List<Order> getOrdersByPeriod(LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy);
    int updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime);
    void shiftEndTimeOrders(int hours, int minutes);
    Order findOrderById(int id);
    int importOrder(String fileName);
    boolean exportOrder(int id, String fileName);
    List<String> toList(Order order);
    void saveState();
    void loadState();
}
