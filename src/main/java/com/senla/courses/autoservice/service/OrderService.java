package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.comparators.order.OrderByCostComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByEndDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderBySubmissionDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByStartDateComparator;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDao orderDao;
    private IMasterService masterService;
    private IGarageService garageService;

    public OrderService (IOrderDao orderDAO, IMasterService masterService, IGarageService garageService) {
        this.orderDao = orderDAO;
        this.masterService = masterService;
        this.garageService = garageService;
    }

    @Override
    public boolean addOrder(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate,
                            String kindOfWork, int cost, int garagePlaceId, String masterName, OrderStatus orderStatus) {
        List<Master> masters = new ArrayList<>();
        masters.add(masterService.findMasterByName(masterName));
        Order order = new Order(id, submissionDate, startDate, endDate, kindOfWork, cost,
                garageService.findGaragePlaceById(garagePlaceId), masters, orderStatus);
        return orderDao.addOrder(order);
    }

    @Override
    public boolean removeOrder(int id) {
        Order order = findOrderById(id);
        return orderDao.removeOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public void cancelOrder(int id) {
        Order order = findOrderById(id);
        orderDao.cancelOrder(order);
    }

    @Override
    public void closeOrder(int id) {
        Order order = findOrderById(id);
        orderDao.closeOrder(order);
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
    public List<Master> getMastersByOrder (int id) {
        Order order = findOrderById(id);
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

    @Override
    public Order findOrderById(int id) {
        for (Order order : getAllOrders()) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
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
            case "bySubmissionDate":
                orderComparator = OrderBySubmissionDateComparator.getInstance();
                break;
            case "byStartDate":
                orderComparator = OrderByStartDateComparator.getInstance();
                break;
        }
        return orderComparator;
    }

}
