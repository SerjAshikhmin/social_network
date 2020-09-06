package com.senla.courses.view.action.orderaction;


import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportOrderAction extends AbstractOrderAction {

    private static final Logger logger = LoggerFactory.getLogger(ImportOrderAction.class);

    public ImportOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (orderController.importOrder(fileName) == 1) {
            logger.info(String.format("Заказ успешно импортирован из файла %s", fileName));
        } else {
            logger.error("При импорте заказа произошла ошибка");
        }
    }
}
