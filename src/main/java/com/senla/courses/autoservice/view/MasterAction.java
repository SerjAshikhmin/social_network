package com.senla.courses.autoservice.view;

import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class MasterAction implements IAction {

    private MasterController masterController;

    public MasterAction(MasterController masterController) {
        this.masterController = masterController;
    }

    @Override
    public void execute(String actionText) {
        switch (actionText) {
            case "добавить мастера":
                addMaster();
                break;
            case "удалить мастера":
                removeMaster();
                break;
            case "список мастеров в алфавитном порядке":
                getAllMastersSorted("byName");
                break;
            case "список мастеров по занятости":
                getAllMastersSorted("byBusy");
                break;
            case "заказ конкретного мастера":
                getCurrentOrder();
                break;
        }
    }

    public void addMaster() {
        String name;
        int id;
        int category;

        ConsoleHelper.writeMessage("Введите номер мастера:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите разряд мастера:");
        category = Integer.parseInt(ConsoleHelper.readString());

        if (masterController.addMaster(id, name, category)) {
            ConsoleHelper.writeMessage(String.format("Мастер %s успешно добавлен", name));
        } else {
            ConsoleHelper.writeMessage("При добавлении мастера произошла ошибка");
        }

    }

    public void removeMaster() {
        String name;
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();

        if (masterController.removeMaster(name)) {
            ConsoleHelper.writeMessage(String.format("Мастер %s успешно удален", name));
        } else {
            ConsoleHelper.writeMessage("При удалении мастера произошла ошибка");
        }
    }

    public void getAllMastersSorted(String sortBy) {
        ConsoleHelper.writeMessage(masterController.getAllMastersSorted(sortBy));
    }

    public void getCurrentOrder() {
        String name;
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();

        Order currentOrder = masterController.getCurrentOrder(name);
        if (currentOrder != null) {
            ConsoleHelper.writeMessage(currentOrder);
        } else {
            ConsoleHelper.writeMessage("Заказ не найден");
        }
    }
}
