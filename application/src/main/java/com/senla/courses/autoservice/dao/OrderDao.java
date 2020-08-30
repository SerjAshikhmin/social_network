package com.senla.courses.autoservice.dao;

import com.lib.dicontainer.annotations.InjectByType;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.dao.jdbcdao.OrderJdbcDao;
import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class OrderDao implements IOrderDao {

    @InjectByType
    private OrderJdbcDao orderJdbcDao;

    @Override
    public int addOrder(Order order) throws SQLException {
        return orderJdbcDao.insert(order);
    }

    @Override
    public int removeOrder(Order order) throws SQLException {
        if (order == null) {
            return 0;
        }
        order.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        return orderJdbcDao.delete(order);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        return orderJdbcDao.findById(id);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return orderJdbcDao.findAll();
    }

    @Override
    public void setAllOrders(List<Order> allOrders) {
        //this.orders = allOrders;
    }

    @Override
    public int updateOrder(Order order) throws SQLException {
        return orderJdbcDao.update(order);
        /*Order daoOrder = getOrderById(order.getId());
        return updateOrderFields(order, daoOrder);*/
    }

    @Override
    public void cancelOrder(Order order) throws SQLException {
        Order daoOrder = orderJdbcDao.findById(order.getId());
        daoOrder.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        daoOrder.setStatus(OrderStatus.CANCELED);
        updateOrder(daoOrder);
    }

    @Override
    public void closeOrder(Order order) throws SQLException {
        Order daoOrder = orderJdbcDao.findById(order.getId());
        daoOrder.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        daoOrder.setEndDate(LocalDateTime.now());
        daoOrder.setStatus(OrderStatus.COMPLETED);
        updateOrder(daoOrder);
    }

    public void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime) throws SQLException {
        Order daoOrder = orderJdbcDao.findById(order.getId());
        daoOrder.setStartDate(newStartTime);
        daoOrder.setEndDate(newEndTime);
        updateOrder(daoOrder);
    }

    public List<Master> getMastersByOrder (Order order) throws OrderNotFoundException {
        if (order != null) {
            return order.getMasters();
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Override
    public void updateAllOrders(List<Order> orders) {
        //this.orders = orders;
    }

    @Override
    public List<Order> getAllOrdersInProgress(Comparator orderComparator) throws SQLException {
        List<Order> completedOrders = getAllOrders().stream()
                .filter(order -> order.getStatus() == OrderStatus.IN_WORK)
                .collect(Collectors.toList());

        if (orderComparator != null) {
            completedOrders.sort(orderComparator);
        }
        return completedOrders;
    }

}
