package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportOrderAction extends AbstractOrderAction {

    private static final Logger logger = LoggerFactory.getLogger(ExportOrderAction.class);

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
            logger.info(String.format("Заказ №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            logger.error("При экпорте заказа произошла ошибка");
        }
    }
}
