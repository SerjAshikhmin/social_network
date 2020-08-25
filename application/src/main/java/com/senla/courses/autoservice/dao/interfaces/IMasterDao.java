package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.sql.SQLException;
import java.util.List;

@Singleton
public interface IMasterDao {

    int addMaster(Master master) throws SQLException;
    int removeMaster(Master master) throws SQLException;
    Master getMasterById(int id) throws SQLException;
    List<Master> getAllMasters() throws SQLException;
    void setAllMasters(List<Master> allMasters);
    int updateMaster(Master master) throws SQLException;
    Order getCurrentOrder(Master master) throws MasterNotFoundException, SQLException;
    List<Master> getAllFreeMasters() throws SQLException;

}
