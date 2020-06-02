package com.senla.courses.autoservice.DAO.interfaces;

import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.util.List;

public interface IMasterDAO {

    boolean addMaster(Master master);
    boolean removeMaster(Master master);
    Master getMasterById(int id);
    List<Master> getAllMasters();
    Master updateMaster(Master master);
    Order getCurrentOrder(Master master);

}
