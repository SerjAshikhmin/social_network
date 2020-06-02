package com.senla.courses.autoservice.service.comparators;

import com.senla.courses.autoservice.model.Master;

import java.util.Comparator;

public class MasterByBusyComparator implements Comparator<Master> {
    @Override
    public int compare(Master master, Master t1) {
        if (master.isBusy() == t1.isBusy()) return 0;
        else
            if (master.isBusy()) return 1;
            else return -1;
    }
}
