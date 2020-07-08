package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.exceptions.MasterNotFoundException;
import com.senla.courses.autoservice.exceptions.WrongFileFormatException;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.service.comparators.master.MasterByBusyComparator;
import com.senla.courses.autoservice.service.comparators.master.MasterByNameComparator;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.autoservice.utils.CsvHelper;
import com.senla.courses.autoservice.utils.SerializeUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MasterService implements IMasterService {

    private IMasterDao masterDao;
    private IOrderDao orderDao;

    public MasterService (IMasterDao masterDao, IOrderDao orderDao) {
        this.masterDao = masterDao;
        this.orderDao = orderDao;
    }

    @Override
    public boolean addMaster(int id, String name, int category) {
        Master master = new Master (id, name, category);
        return masterDao.addMaster(master);
    }

    @Override
    public boolean removeMaster(String name) {
        return masterDao.removeMaster(findMasterByName(name));
    }

    @Override
    public List<Master> getAllMasters() {
        return masterDao.getAllMasters();
    }

    @Override
    public List<Master> getAllMastersSorted(String sortBy) {
        List<Master> allMastersSorted = new ArrayList<>();
        allMastersSorted.addAll(masterDao.getAllMasters());

        Comparator masterComparator = getMasterComparator(sortBy);
        if (masterComparator != null) {
            allMastersSorted.sort(masterComparator);
        }
        return allMastersSorted;
    }

    @Override
    public List<Master> getAllFreeMasters() {
        return masterDao.getAllFreeMasters();
    }

    @Override
    public Order getCurrentOrder(String name) {
        try {
            return masterDao.getCurrentOrder(findMasterByName(name));
        } catch (MasterNotFoundException e) {
            ConsoleHelper.writeMessage("Мастер не найден");
            return null;
        }
    }

    @Override
    public Master findMasterByName(String name) {
        for (Master master : getAllMasters()) {
            if (master.getName().equals(name)) {
                return master;
            }
        }
        return null;
    }

    @Override
    public Master findMasterById(int id) {
        return masterDao.getMasterById(id);
    }

    @Override
    public boolean importMaster(String fileName) {
        try {
            List<String> masterDataList = CsvHelper.importCsvFile(fileName);
            if (masterDataList == null) {
                throw new FileNotFoundException();
            }
            Master importMaster = new Master(Integer.parseInt(masterDataList.get(0)), masterDataList.get(1), Integer.parseInt(masterDataList.get(2)),
                    Boolean.parseBoolean(masterDataList.get(3)), orderDao.getOrderById(Integer.parseInt(masterDataList.get(4))));

            if (masterDao.getMasterById(importMaster.getId()) != null) {
                masterDao.updateMaster(importMaster);
                return true;
            } else {
                return masterDao.addMaster(importMaster);
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
            return false;
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Файл не найден");
            return false;
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Файл содержит неверные данные");
            return false;
        }
    }

    @Override
    public boolean exportMaster(int id, String fileName) {
        Master masterToExport = masterDao.getMasterById(id);
        try {
            if (masterToExport != null) {
                return CsvHelper.exportCsvFile(toList(masterToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № мастера");
                return false;
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
            return false;
        }
    }

    @Override
    public List<String> toList(Master master) {
        List<String> masterAsList = new ArrayList<>();
        masterAsList.add(String.valueOf(master.getId()));
        masterAsList.add(master.getName());
        masterAsList.add(String.valueOf(master.getCategory()));
        masterAsList.add(String.valueOf(master.isBusy()));
        masterAsList.add(String.valueOf(master.getCurrentOrder().getId()));
        return masterAsList;
    }

    @Override
    public void saveState() {
        SerializeUtil.saveState(getAllMasters(), "SerialsMasters.out");
    }

    @Override
    public void loadState() {
        masterDao.setAllMasters(SerializeUtil.loadState(Master.class, "SerialsMasters.out"));
    }

    private Comparator getMasterComparator(String sortBy) {
        Comparator masterComparator = null;
        switch (sortBy) {
            case "byName":
                masterComparator = MasterByNameComparator.getInstance();
                break;
            case "byBusy":
                masterComparator = MasterByBusyComparator.getInstance();
                break;
        }
        return masterComparator;
    }

}
