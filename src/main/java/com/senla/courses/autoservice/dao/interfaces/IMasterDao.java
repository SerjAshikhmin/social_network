package com.senla.courses.autoservice.dao.interfaces;

import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.List;

public interface IMasterDao {

    boolean addMaster(Master master);
    boolean removeMaster(Master master);
    Master getMasterById(int id);
    List<Master> getAllMasters();
    Master updateMaster(Master master);
    Order getCurrentOrder(Master master) throws MasterNotFoundException;
    List<Master> getAllFreeMasters();

}
