package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.view.action.masteraction.AddMasterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveOrderAction extends AbstractOrderAction {

    private static final Logger logger = LoggerFactory.getLogger(RemoveOrderAction.class);

    public RemoveOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер заказа:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        if (orderController.removeOrder(orderId) == 1) {
            logger.info(String.format("Заказ №%d успешно удален", orderId));
        } else {
            logger.error("При удалении заказа произошла ошибка");
        }
    }
}
