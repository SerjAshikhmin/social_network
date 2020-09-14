package com.senla.courses.autoservice.dao.interfaces;

import com.lib.dicontainer.annotations.Singleton;
import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import javax.persistence.PersistenceException;
import java.util.List;

@Singleton
public interface IMasterDao {

    int addMaster(Master master) throws PersistenceException;
    int removeMaster(Master master) throws PersistenceException;
    Master getMasterById(int id) throws PersistenceException;
    List<Master> getAllMasters() throws PersistenceException;
    void setAllMasters(List<Master> allMasters);
    int updateMaster(Master master) throws PersistenceException;
    Order getCurrentOrder(Master master) throws MasterNotFoundException, PersistenceException;
    List<Master> getAllFreeMasters() throws PersistenceException;

}
