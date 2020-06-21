package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.util.List;

public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    public boolean addOrder(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate,
                            String kindOfWork, int cost,int garageId, int garagePlaceId, String masterName, OrderStatus orderStatus) {
        return orderService.addOrder(id, submissionDate, startDate, endDate, kindOfWork, cost, garageId, garagePlaceId, masterName, orderStatus);
    }

    public boolean removeOrder(int id) {
        return orderService.removeOrder(id);
    }

    public void cancelOrder(int id) {
        orderService.cancelOrder(id);
    }

    public void closeOrder(int id) {
        orderService.closeOrder(id);
    }

    public void shiftEndTimeOrders(int hours, int minutes) {
        orderService.shiftEndTimeOrders(hours, minutes);
    }

    public List<Order> getAllOrdersSorted(String sortBy) {
        return orderService.getAllOrdersSorted(sortBy);
    }

    public List<Order> getAllOrdersInProgress(String sortBy) {
        return orderService.getAllOrdersInProgress(sortBy);
    }

    public List<Master> getMastersByOrder (int id) {
        return orderService.getMastersByOrder(id);
    }

    public List<Order> getOrdersByPeriod (LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy) {
        return orderService.getOrdersByPeriod(startPeriod, endPeriod, sortBy);
    }

    public LocalDateTime getNearestFreeDate() {
        return orderService.getNearestFreeDate();
    }

    public boolean importOrder(String fileName) {
        return orderService.importOrder(fileName);
    }

    public boolean exportOrder(int id, String fileName) {
        return orderService.exportOrder(id, fileName);
    }
}
