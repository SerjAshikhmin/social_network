package com.senla.courses.view.action.orderaction;


import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class ImportOrderAction extends AbstractOrderAction {

    public ImportOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (orderController.importOrder(fileName)) {
            ConsoleHelper.writeMessage(String.format("Заказ успешно импортирован из файла %s", fileName));
        } else {
            ConsoleHelper.writeMessage("При импорте заказа произошла ошибка");
        }
    }
}
