package com.senla.courses.autoservice.service.comparators;

import com.senla.courses.autoservice.model.Master;

import java.util.Comparator;

public class MasterByNameComparator implements Comparator<Master> {
    @Override
    public int compare(Master master, Master t1) {
        return master.getName().compareTo(t1.getName());
    }
}
