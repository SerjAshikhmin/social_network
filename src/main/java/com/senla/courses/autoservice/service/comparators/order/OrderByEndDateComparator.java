package com.senla.courses.autoservice.service.comparators.order;

import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;

public class OrderByEndDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order order, Order t1) {
        return order.getEndDate().compareTo(t1.getEndDate());
    }
}
