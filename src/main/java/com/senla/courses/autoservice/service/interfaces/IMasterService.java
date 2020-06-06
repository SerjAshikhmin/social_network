package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.Comparator;
import java.util.List;

public interface IMasterService {

    boolean addMaster(Master master);
    boolean removeMaster(Master master);
    List<Master> getAllMasters();
    List<Master> getAllMastersSorted(String sortBy);
    List<Master> getAllFreeMasters();
    Order getCurrentOrder(Master master);

}
