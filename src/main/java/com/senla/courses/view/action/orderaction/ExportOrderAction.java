package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class ExportOrderAction extends AbstractOrderAction {

    public ExportOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int id;
        String fileName;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (orderController.exportOrder(id, fileName)) {
            ConsoleHelper.writeMessage(String.format("Заказ №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            ConsoleHelper.writeMessage("При экпорте заказа произошла ошибка");
        }
    }
}
