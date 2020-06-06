package com.senla.courses.autoservice.service.comparators.order;

import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;

public class OrderByCostComparator implements Comparator<Order> {
    @Override
    public int compare(Order order, Order t1) {
        if(order.getCost() > t1.getCost())
            return 1;
        else if(order.getCost()< t1.getCost())
            return -1;
        else
            return 0;
    }
}
