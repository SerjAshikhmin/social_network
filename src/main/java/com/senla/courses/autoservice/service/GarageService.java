package com.senla.courses.autoservice.service;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.exceptions.WrongFileFormatException;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.autoservice.utils.CsvHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GarageService implements IGarageService {

    private IGarageDao garageDao;
    private IMasterService masterService;

    public GarageService(IGarageDao garageDAO, IMasterService masterService) {
        this.garageDao = garageDAO;
        this.masterService = masterService;
    }

    @Override
    public boolean addGarage(int id, String address) {
        Garage garage = new Garage(id, address, new ArrayList<>());
        return garageDao.addGarage(garage);
    }

    @Override
    public boolean removeGarage(int garageId) {
        return garageDao.removeGarage(findGarageById(garageId));
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageDao.getAllGarages();
    }

    @Override
    public boolean addGaragePlace(int garageId, int garagePlaceId, String type, int area) {
        GaragePlace garagePlace = new GaragePlace(garagePlaceId, garageId, type, area);
        return garageDao.addGaragePlace(garagePlace);
    }

    @Override
    public boolean removeGaragePlace(int garageId, int garagePlaceId) {
        return garageDao.removeGaragePlace(findGarageById(garageId), findGaragePlaceById(garageId, garagePlaceId));
    }

    @Override
    public List<GaragePlace> getAllFreePlaces() {
        List<GaragePlace> freePlaces = new ArrayList<>();
        garageDao.getAllGarages().stream()
                .forEach(garage -> garage.getGaragePlaces().stream()
                        .filter(garagePlace -> !garagePlace.isBusy())
                        .forEach(garagePlace -> freePlaces.add(garagePlace)));

        return freePlaces;
    }

    @Override
    public int getFreePlacesCountInFuture() {
        return Math.min(getAllFreePlaces().size(), masterService.getAllFreeMasters().size());
    }

    @Override
    public GaragePlace findGaragePlaceById(int garageId, int garagePlaceId) {
        return garageDao.getGaragePlaceById(garageId, garagePlaceId);
    }

    @Override
    public Garage findGarageById(int garageId) {
        return garageDao.getGarageById(garageId);
    }

    @Override
    public boolean importGarage(String fileName) {
        Garage importGarage;
        try {
            List<String> garageDataList = CsvHelper.importCsvFile(fileName);
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

            if (garageDao.getGarageById(importGarage.getId()) != null) {
                garageDao.updateGarage(importGarage);
                return true;
            } else {
                return garageDao.addGarage(importGarage);
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
    public boolean exportGarage(int id, String fileName) {
        Garage garageToExport = garageDao.getGarageById(id);
        try {
            if (garageToExport != null) {
                return CsvHelper.exportCsvFile(garageToList(garageToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № гаража");
                return false;
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
            return false;
        }
    }

    @Override
    public boolean importGaragePlace(String fileName) {
        try {
            List<String> garagePlaceDataList = CsvHelper.importCsvFile(fileName);
            if (garagePlaceDataList == null) {
                throw new FileNotFoundException();
            }
            GaragePlace importGaragePlace = new GaragePlace(Integer.parseInt(garagePlaceDataList.get(0)), Integer.parseInt(garagePlaceDataList.get(1)),
                    garagePlaceDataList.get(2), Integer.parseInt(garagePlaceDataList.get(3)), Boolean.parseBoolean(garagePlaceDataList.get(4)));

            if (garageDao.getGaragePlaceById(importGaragePlace.getGarageId(), importGaragePlace.getId()) != null) {
                garageDao.updateGaragePlace(importGaragePlace);
                return true;
            } else {
                return garageDao.addGaragePlace(importGaragePlace);
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
    public boolean exportGaragePlace(int garageId, int garagePlaceId, String fileName) {
        GaragePlace garagePlaceToExport = garageDao.getGaragePlaceById(garageId, garagePlaceId);
        try {
            if (garagePlaceToExport != null) {
                return CsvHelper.exportCsvFile(garagePlaceToList(garagePlaceToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № гаража или места в гараже");
                return false;
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
            return false;
        }
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
        List<Garage> allGarages = getAllGarages();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SerialsGarages.out"))) {
            out.writeInt(allGarages.size());
            for (Garage garage: allGarages) {
                out.writeObject(garage);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка ввода/вывода");
        }
    }

    @Override
    public void loadState() {
        List<Garage> allGarages = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerialsGarages.out"))) {
            Garage garage;
            int numberOfGarages = ois.readInt();
            for (int i = 0; i < numberOfGarages; i++) {
                garage = (Garage) ois.readObject();
                allGarages.add(garage);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            ConsoleHelper.writeMessage("Ошибка десериализации");
        } finally {
            garageDao.setAllGarages(allGarages);
        }
    }

}
