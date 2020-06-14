package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.List;

public interface IMasterService {

    boolean addMaster(int id, String name, int category);
    boolean removeMaster(String name);
    List<Master> getAllMasters();
    List<Master> getAllMastersSorted(String sortBy);
    List<Master> getAllFreeMasters();
    Order getCurrentOrder(String name);
    Master findMasterByName(String name);

}
