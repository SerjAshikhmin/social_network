package com.senla.courses.autoservice.service;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.InjectProperty;
import com.lib.utils.ConsoleHelper;
import com.lib.utils.CsvUtil;
import com.lib.utils.exceptions.WrongFileFormatException;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.jdbcdao.DbJdbcConnector;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.utils.SerializeUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarageService implements IGarageService {

    @InjectByType
    private IGarageDao garageDao;
    @InjectByType
    private IMasterService masterService;
    @InjectProperty
    private boolean addGaragePlaceOption;
    @InjectProperty
    private boolean removeGaragePlaceOption;

    @Override
    public int addGarage(int id, String address) {
        Garage garage = new Garage(id, address, new ArrayList<>());
        try {
            return garageDao.addGarage(garage);
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            return 0;
        }
    }

    @Override
    public int removeGarage(int garageId) {
        try {
            return garageDao.removeGarage(findGarageById(garageId));
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            return 0;
        }
    }

    @Override
    public List<Garage> getAllGarages() {
        List<Garage> garages = null;
        Connection connection = DbJdbcConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            garages = garageDao.getAllGarages();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка отмены транзакции");
            }
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return garages;
    }

    @Override
    public int addGaragePlace(int garageId, int garagePlaceId, String type, int area) {
        if (addGaragePlaceOption) {
            GaragePlace garagePlace = new GaragePlace(garagePlaceId, garageId, type, area);
            try {
                return garageDao.addGaragePlace(garagePlace);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        } else {
            ConsoleHelper.writeMessage("Возможность добавления места в гараже отключена");
        }
        return 0;
    }

    @Override
    public int removeGaragePlace(int garageId, int garagePlaceId) {
        if (removeGaragePlaceOption) {
            try {
                return garageDao.removeGaragePlace(findGaragePlaceById(garageId, garagePlaceId));
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        } else {
            ConsoleHelper.writeMessage("Возможность удаления места в гараже отключена");
        }
        return 0;
    }

    @Override
    public List<GaragePlace> getAllFreePlaces() {
        List<GaragePlace> freePlaces = new ArrayList<>();
        Connection connection = DbJdbcConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            garageDao.getAllGarages().stream()
                    .forEach(garage -> garage.getGaragePlaces().stream()
                            .filter(garagePlace -> !garagePlace.isBusy())
                            .forEach(garagePlace -> freePlaces.add(garagePlace)));
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка отмены транзакции");
            }
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return freePlaces;
    }

    @Override
    public int getFreePlacesCountInFuture() {
        return Math.min(getAllFreePlaces().size(), masterService.getAllFreeMasters().size());
    }

    @Override
    public GaragePlace findGaragePlaceById(int garageId, int garagePlaceId) {
        GaragePlace garagePlace = null;
        Connection connection = DbJdbcConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            garagePlace = garageDao.getGaragePlaceById(garageId, garagePlaceId);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка отмены транзакции");
            }
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return garagePlace;
    }

    @Override
    public Garage findGarageById(int garageId) {
        Garage garage = null;
        Connection connection = DbJdbcConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            garage = garageDao.getGarageById(garageId);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка отмены транзакции");
            }
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return garage;
    }

    @Override
    public int importGarage(String fileName) {
        Garage importGarage;
        try {
            List<String> garageDataList = CsvUtil.importCsvFile(fileName);
            if (garageDataList == null) {
                throw new FileNotFoundException();
            }
            if (garageDataList.get(2).equals("null")) {
                importGarage = new Garage(Integer.parseInt(garageDataList.get(0)), garageDataList.get(1), null);
            } else {
                List<GaragePlace> importGaragePlaces = new ArrayList<>();
                for (int i = 0; i < (garageDataList.size() - 2) / 5; i++) {
                    GaragePlace importGaragePlace = new GaragePlace(Integer.parseInt(garageDataList.get(2 + i * 5)), Integer.parseInt(garageDataList.get(3 + i * 5)),
                            garageDataList.get(4 + i * 5), Integer.parseInt(garageDataList.get(5 + i * 5)), Boolean.parseBoolean(garageDataList.get(6 + i * 5)));
                    importGaragePlaces.add(importGaragePlace);
                }
                importGarage = new Garage(Integer.parseInt(garageDataList.get(0)), garageDataList.get(1), importGaragePlaces);
            }

            if (findGarageById(importGarage.getId()) != null) {
                garageDao.updateGarage(importGarage);
                return 1;
            } else {
                return garageDao.addGarage(importGarage);
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Файл не найден");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Файл содержит неверные данные");
        }
        return 0;
    }

    @Override
    public boolean exportGarage(int id, String fileName) {
        try {
            Garage garageToExport = findGarageById(id);
            if (garageToExport != null) {
                return CsvUtil.exportCsvFile(garageToList(garageToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № гаража");
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
        }
        return false;
    }

    @Override
    public int importGaragePlace(String fileName) {
        Connection connection = DbJdbcConnector.getConnection();
        try {
            List<String> garagePlaceDataList = CsvUtil.importCsvFile(fileName);
            if (garagePlaceDataList == null) {
                throw new FileNotFoundException();
            }
            GaragePlace importGaragePlace = new GaragePlace(Integer.parseInt(garagePlaceDataList.get(0)), Integer.parseInt(garagePlaceDataList.get(1)),
                    garagePlaceDataList.get(2), Integer.parseInt(garagePlaceDataList.get(3)), Boolean.parseBoolean(garagePlaceDataList.get(4)));

            connection.setAutoCommit(false);
            if (garageDao.getGaragePlaceById(importGaragePlace.getGarageId(), importGaragePlace.getId()) != null) {
                garageDao.updateGaragePlace(importGaragePlace);
            } else {
                garageDao.addGaragePlace(importGaragePlace);
            }
            connection.commit();
            return 1;
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Файл не найден");
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка отмены транзакции");
            }
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Файл содержит неверные данные");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return 0;
    }

    @Override
    public boolean exportGaragePlace(int garageId, int garagePlaceId, String fileName) {
        try {
            GaragePlace garagePlaceToExport = findGaragePlaceById(garageId, garagePlaceId);
            if (garagePlaceToExport != null) {
                return CsvUtil.exportCsvFile(garagePlaceToList(garagePlaceToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № гаража или места в гараже");
                return false;
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
        }
        return false;
}

    @Override
    public List<String> garageToList(Garage garage) {
        List<String> garageAsList = new ArrayList<>();
        garageAsList.add(String.valueOf(garage.getId()));
        garageAsList.add(garage.getAddress());
        if (garage.getGaragePlaces().size() != 0) {
            garage.getGaragePlaces().stream()
                    .forEach(garagePlace -> garageAsList.addAll(garagePlaceToList(garagePlace)));
        } else {
            garageAsList.add("null");
        }
        return garageAsList;
    }

    @Override
    public List<String> garagePlaceToList(GaragePlace garagePlace) {
        List<String> garagePlaceAsList = new ArrayList<>();
        garagePlaceAsList.add(String.valueOf(garagePlace.getId()));
        garagePlaceAsList.add(String.valueOf(garagePlace.getGarageId()));
        garagePlaceAsList.add(garagePlace.getType());
        garagePlaceAsList.add(String.valueOf(garagePlace.getArea()));
        garagePlaceAsList.add(String.valueOf(garagePlace.isBusy()));
        return garagePlaceAsList;
    }

    @Override
    public void saveState() {
        SerializeUtil.saveState(getAllGarages(), "SerialsGarages.out");
    }

    @Override
    public void loadState() {
        garageDao.setAllGarages(SerializeUtil.loadState(Garage.class, "SerialsGarages.out"));
    }

}
