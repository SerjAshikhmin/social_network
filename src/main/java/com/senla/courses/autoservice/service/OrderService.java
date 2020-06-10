package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.comparators.order.OrderByCostComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByEndDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByPlannedStartDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByStartDateComparator;
import com.senla.courses.autoservice.service.interfaces.IOrderService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDao orderDAO;

    public OrderService (IOrderDao orderDAO) {
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
    public List<Order> getAllOrdersSorted(String sortBy) {
        List<Order> allOrdersSorted = new ArrayList<>();
        allOrdersSorted.addAll(orderDAO.getAllOrders());

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            allOrdersSorted.sort(orderComparator);
        }
        return allOrdersSorted;
    }

    @Override
    public List<Order> getAllOrdersInProgress(String sortBy) {
        Comparator orderComparator = getOrderComparator(sortBy);
        return orderDAO.getAllOrdersInProgress(orderComparator);
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
    public List<Order> getOrdersByPeriod (GregorianCalendar startPeriod, GregorianCalendar endPeriod, String sortBy) {
        List<Order> ordersByPeriod = new ArrayList<>();
        for (Order order : orderDAO.getAllOrders()) {
            if (startPeriod.compareTo(order.getEndDate()) == -1 && endPeriod.compareTo(order.getEndDate()) == 1) {
                ordersByPeriod.add(order);
            }
        }

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            ordersByPeriod.sort(orderComparator);
        }
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

    private Comparator getOrderComparator(String sortBy) {
        Comparator orderComparator = null;
        switch (sortBy) {
            case "byCost":
                orderComparator = OrderByCostComparator.getInstance();
                break;
            case "byEndDate":
                orderComparator = OrderByEndDateComparator.getInstance();
                break;
            case "byPlannedStartDate":
                orderComparator = OrderByPlannedStartDateComparator.getInstance();
                break;
            case "byStartDate":
                orderComparator = OrderByStartDateComparator.getInstance();
                break;
        }
        return orderComparator;
    }

}
