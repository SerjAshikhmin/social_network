package com.senla.courses.autoservice.view;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GarageAction implements IAction {

    private GarageController garageController;

    public GarageAction(GarageController garageController) {
        this.garageController = garageController;
    }

    @Override
    public void execute(String actionText) {
        switch (actionText) {
            case "добавить гараж":
                addGarage();
                break;
            case "удалить гараж":
                removeGarage();
                break;
            case "добавить место в гараже":
                addGaragePlace();
                break;
            case "удалить место в гараже":
                removeGaragePlace();
                break;
            case "список свободных мест в гаражах":
                getAllFreePlaces();
                break;
            case "кол-во свободных мест на любую дату в будущем":
                getFreePlacesCountInFuture();
                break;
        }
    }

    public void addGarage() {
        String address;
        int id;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите адрес гаража:");
        address = ConsoleHelper.readString();

        if (garageController.addGarage(id, address)) {
            ConsoleHelper.writeMessage(String.format("Гараж №%d успешно добавлен", id));
        } else {
            ConsoleHelper.writeMessage("При добавлении гаража произошла ошибка");
        }
    }

    public void addGaragePlace() {
        String type;
        int garageId;
        int garagePlaceId;
        int area;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите тип места в гараже:");
        type = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите площадь:");
        area = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.addGaragePlace(garageId, garagePlaceId, type, area)) {
            ConsoleHelper.writeMessage(String.format("Место в гараже №%d успешно добавлено", garagePlaceId));
        } else {
            ConsoleHelper.writeMessage("При добавлении места в гараже произошла ошибка");
        }
    }

    public void removeGarage() {
        int garageId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGarage(garageId)) {
            ConsoleHelper.writeMessage(String.format("Гараж %d успешно удален", garageId));
        } else {
            ConsoleHelper.writeMessage("При удалении гаража произошла ошибка");
        }
    }

    public void removeGaragePlace() {
        int garageId;
        int garagePlaceId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGaragePlace(garageId, garagePlaceId)) {
            ConsoleHelper.writeMessage(String.format("Место №%d в гараже №%d успешно удалено", garagePlaceId, garageId));
        } else {
            ConsoleHelper.writeMessage("При удалении места в гараже произошла ошибка");
        }
    }

    public void getAllFreePlaces() {
        ConsoleHelper.writeMessage(garageController.getAllFreePlaces());
    }

    public void getFreePlacesCountInFuture() {
        ConsoleHelper.writeMessage(garageController.getFreePlacesCountInFuture());
    }
}
