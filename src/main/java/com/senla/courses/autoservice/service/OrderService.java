package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.comparators.order.OrderByCostComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByEndDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByPlannedStartDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByStartDateComparator;
import com.senla.courses.autoservice.service.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDao orderDao;

    public OrderService (IOrderDao orderDAO) {
        this.orderDao = orderDAO;
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public boolean removeOrder(Order order) {
        return orderDao.removeOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public void cancelOrder(Order order) {
        orderDao.cancelOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getAllOrdersSorted(String sortBy) {
        List<Order> allOrdersSorted = new ArrayList<>();
        allOrdersSorted.addAll(orderDao.getAllOrders());

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            allOrdersSorted.sort(orderComparator);
        }
        return allOrdersSorted;
    }

    @Override
    public List<Order> getAllOrdersInProgress(String sortBy) {
        Comparator orderComparator = getOrderComparator(sortBy);
        return orderDao.getAllOrdersInProgress(orderComparator);
    }

    @Override
    public LocalDateTime getNearestFreeDate() {
        List<Order> allOrders = orderDao.getAllOrders();
        final LocalDateTime nearestFreeDate = allOrders.get(0).getEndDate();
        return allOrders.stream()
                .filter(order -> order.getEndDate().compareTo(nearestFreeDate) == -1)
                .findFirst().get().getEndDate();
    }

    @Override
    public List<Master> getMastersByOrder (Order order) {
        return orderDao.getMastersByOrder(order);
    }

    @Override
    public List<Order> getOrdersByPeriod (LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy) {
        List<Order> ordersByPeriod = new ArrayList<>();
        orderDao.getAllOrders().stream()
                .filter(order -> startPeriod.compareTo(order.getEndDate()) == -1 && endPeriod.compareTo(order.getEndDate()) == 1)
                .forEach(order -> ordersByPeriod.add(order));

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            ordersByPeriod.sort(orderComparator);
        }
        return ordersByPeriod;
    }

    @Override
    public void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        orderDao.updateOrderTime(order, newStartTime, newEndTime);
    }

    @Override
    public void shiftEndTimeOrders(int hours, int minutes) {
        List<Order> allOrders = orderDao.getAllOrders();
        allOrders.stream().forEach(order -> {
            orderDao.updateOrderTime(order, order.getStartDate(), order.getEndDate().plusHours(hours).plusMinutes(minutes));
        });
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
