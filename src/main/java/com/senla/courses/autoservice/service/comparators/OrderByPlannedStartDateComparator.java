package com.senla.courses.autoservice.service.comparators;

import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;

public class OrderByPlannedStartDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order order, Order t1) {
        return order.getPlannedStartDate().compareTo(t1.getPlannedStartDate());
    }
}
