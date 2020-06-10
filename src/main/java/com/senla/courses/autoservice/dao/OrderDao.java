package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;


public class OrderDao implements IOrderDao {

    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    @Override
    public boolean removeOrder(Order order) {
        order.getGaragePlace().setBusy(false);
        for (Master master : order.getMasters()) {
            master.setBusy(false);
        }
        return orders.remove(order);
    }

    @Override
    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order updateOrder(Order order) {
        Order daoOrder = getOrderById(order.getId());
        return updateOrderFields(order, daoOrder);
    }

    @Override
    public void cancelOrder(Order order) {
        Order daoOrder = getOrderById(order.getId());
        daoOrder.getGaragePlace().setBusy(false);
        for (Master master : daoOrder.getMasters()) {
            master.setBusy(false);
        }
        daoOrder.setStatus(OrderStatus.CANCELED);
    }

    public void updateOrderTime(Order order, GregorianCalendar newStartTime, GregorianCalendar newEndTime) {
        Order daoOrder = getOrderById(order.getId());
        daoOrder.setStartDate(newStartTime);
        daoOrder.setEndDate(newEndTime);
    }

    public List<Master> getMastersByOrder (Order order) {
        return order.getMasters();
    }

    @Override
    public void updateAllOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> getAllOrdersInProgress(Comparator orderComparator) {
        List<Order> completedOrders = new ArrayList<>();
        for (Order order : getAllOrders()) {
            if (order.getStatus() == OrderStatus.IN_WORK) {
                completedOrders.add(order);
            }
        }

        if (orderComparator != null) {
            completedOrders.sort(orderComparator);
        }
        return completedOrders;
    }

    private Order updateOrderFields(Order order, Order daoOrder) {
        daoOrder.setCost(order.getCost());
        daoOrder.setStartDate(order.getStartDate());
        daoOrder.setEndDate(order.getEndDate());
        daoOrder.setKindOfWork(order.getKindOfWork());
        daoOrder.setGaragePlace(order.getGaragePlace());
        daoOrder.setMasters(order.getMasters());
        daoOrder.setStatus(order.getStatus());
        return daoOrder;
    }

}
