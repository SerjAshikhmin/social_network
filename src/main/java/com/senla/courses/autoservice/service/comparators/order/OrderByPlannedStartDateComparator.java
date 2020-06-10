package com.senla.courses.autoservice.service.comparators.order;

import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;

public class OrderByPlannedStartDateComparator implements Comparator<Order> {

    private static OrderByPlannedStartDateComparator instance;

    private OrderByPlannedStartDateComparator() {}

    public static OrderByPlannedStartDateComparator getInstance() {
        if (instance == null) {
            instance = new OrderByPlannedStartDateComparator();
        }
        return instance;
    }

    @Override
    public int compare(Order order, Order t1) {
        return order.getPlannedStartDate().compareTo(t1.getPlannedStartDate());
    }
}
