package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.dao.jpadao.AbstractJpaDao;
import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class OrderDao extends AbstractJpaDao<Order> implements IOrderDao {

    public OrderDao() {
        super(Order.class);
    }

    @Override
    public int addOrder(Order order) throws PersistenceException {
        return insert(order);
    }

    @Override
    public int removeOrder(Order order) throws PersistenceException {
        if (order == null) {
            return 0;
        }
        order.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        return delete(order);
    }

    @Override
    public Order getOrderById(int id) throws PersistenceException {
        return findById(id);
    }

    @Override
    public List<Order> getAllOrders() throws PersistenceException {
        return findAll();
    }

    @Override
    public void setAllOrders(List<Order> allOrders) {
        //this.orders = allOrders;
    }

    @Override
    public int updateOrder(Order order) throws PersistenceException {
        return update(order);
        /*Order daoOrder = getOrderById(order.getId());
        return updateOrderFields(order, daoOrder);*/
    }

    @Override
    public void cancelOrder(Order order) throws PersistenceException {
        Order daoOrder = findById(order.getId());
        daoOrder.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        daoOrder.setStatus(OrderStatus.CANCELED);
        updateOrder(daoOrder);
    }

    @Override
    public void closeOrder(Order order) throws PersistenceException {
        Order daoOrder = findById(order.getId());
        daoOrder.getGaragePlace().setBusy(false);
        order.getMasters().stream()
                .forEach(master -> master.setBusy(false));
        daoOrder.setEndDate(LocalDateTime.now());
        daoOrder.setStatus(OrderStatus.COMPLETED);
        updateOrder(daoOrder);
    }

    public void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime) throws PersistenceException {
        Order daoOrder = findById(order.getId());
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
    public List<Order> getAllOrdersInProgress(Comparator orderComparator) throws PersistenceException {
        List<Order> completedOrders = getAllOrders().stream()
                .filter(order -> order.getStatus() == OrderStatus.IN_WORK)
                .collect(Collectors.toList());

        if (orderComparator != null) {
            completedOrders.sort(orderComparator);
        }
        return completedOrders;
    }

}
