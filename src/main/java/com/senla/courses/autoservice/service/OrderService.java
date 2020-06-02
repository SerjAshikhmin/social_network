package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.DAO.interfaces.IOrderDAO;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IOrderService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDAO orderDAO;

    public OrderService (IOrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDAO.addOrder(order);
    }

    @Override
    public boolean removeOrder(Order order) {
        return orderDAO.removeOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    @Override
    public void cancelOrder(Order order) {
        orderDAO.cancelOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getAllOrders(Comparator orderComparator) {
        List<Order> allOrders = orderDAO.getAllOrders();
        allOrders.sort(orderComparator);
        return allOrders;
    }

    @Override
    public List<Order> getAllOrdersInProgress(Comparator orderComparator) {
        List<Order> completedOrders = new ArrayList<>();
        for (Order order : orderDAO.getAllOrders()) {
            if (order.getStatus() == OrderStatus.IN_WORK) {
                completedOrders.add(order);
            }
        }

        completedOrders.sort(orderComparator);
        return completedOrders;
    }

    @Override
    public GregorianCalendar getNearestFreeDate() {
        List<Order> allOrders = orderDAO.getAllOrders();
        GregorianCalendar nearestFreeDate = allOrders.get(0).getEndDate();
        for (Order order : allOrders) {
            if (order.getEndDate().compareTo(nearestFreeDate) == -1) {
                nearestFreeDate = order.getEndDate();
            }
        }
        return nearestFreeDate;
    }

    @Override
    public List<Master> getMastersByOrder (Order order) {
        return orderDAO.getMastersByOrder(order);
    }

    @Override
    public List<Order> getOrdersByPeriod (GregorianCalendar startPeriod, GregorianCalendar endPeriod, Comparator orderComparator) {
        List<Order> ordersByPeriod = new ArrayList<>();
        for (Order order : orderDAO.getAllOrders()) {
            if (startPeriod.compareTo(order.getEndDate()) == -1 && endPeriod.compareTo(order.getEndDate()) == 1) {
                ordersByPeriod.add(order);
            }
        }

        ordersByPeriod.sort(orderComparator);
        return ordersByPeriod;
    }

    @Override
    public void updateOrderTime(Order order, GregorianCalendar newStartTime, GregorianCalendar newEndTime) {
        orderDAO.updateOrderTime(order, newStartTime, newEndTime);
    }

    @Override
    public void shiftEndTimeOrders(int hours, int minutes) {
        List<Order> allOrders = orderDAO.getAllOrders();
        for (Order order : allOrders) {
            order.getEndDate().add(GregorianCalendar.HOUR, hours);
            order.getEndDate().add(GregorianCalendar.MINUTE, minutes);
        }
        orderDAO.updateAllOrders(allOrders);
    }

}
