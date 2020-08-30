package com.senla.courses.autoservice.dao;

import com.lib.dicontainer.annotations.InjectByType;
import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.dao.jdbcdao.MasterJdbcDao;
import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MasterDao implements IMasterDao {

    @InjectByType
    private MasterJdbcDao masterJdbcDao;
    @InjectByType
    IOrderDao orderDao;

    @Override
    public int addMaster(Master master) throws SQLException {
        return masterJdbcDao.insert(master);
    }

    @Override
    public int removeMaster(Master master) throws SQLException {
        return masterJdbcDao.delete(master);
    }

    @Override
    public Master getMasterById(int id) throws SQLException {
        return masterJdbcDao.findById(id);
    }

    @Override
    public List<Master> getAllMasters() throws SQLException {
        return masterJdbcDao.findAll();
    }

    @Override
    public void setAllMasters(List<Master> allMasters) {
        //this.masters = allMasters;
    }

    @Override
    public int updateMaster(Master master) throws SQLException {
        return masterJdbcDao.update(master);
        /*Master daoMaster = getMasterById(master.getId());
        return updateMasterFields(master, daoMaster);*/
    }

    public Order getCurrentOrder(Master master) throws MasterNotFoundException, SQLException {
        if (master != null) {
            return orderDao.getOrderById(master.getOrderId());
        } else {
            throw new MasterNotFoundException();
        }
    }

    @Override
    public List<Master> getAllFreeMasters() throws SQLException {
        return getAllMasters().stream()
                .filter(master -> !master.isBusy())
                .collect(Collectors.toList());
    }

}
