package com.senla.courses.autoservice.service;

import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.InjectProperty;
import com.lib.utils.ConsoleHelper;
import com.lib.utils.CsvUtil;
import com.lib.utils.exceptions.WrongFileFormatException;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.exceptions.OrderNotFoundException;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.comparators.order.OrderByCostComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByEndDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderBySubmissionDateComparator;
import com.senla.courses.autoservice.service.comparators.order.OrderByStartDateComparator;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import com.senla.courses.autoservice.utils.SerializeUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService implements IOrderService {

    @InjectByType
    private IOrderDao orderDao;
    @InjectByType
    private IMasterService masterService;
    @InjectByType
    private IGarageService garageService;
    @InjectProperty
    private boolean shiftEndTimeOrdersOption;
    @InjectProperty
    private boolean removeOrderOption;

    @Override
    public boolean addOrder(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate,
                            String kindOfWork, int cost, int garageId, int garagePlaceId, String masterName, OrderStatus orderStatus) {
        List<Master> masters = new ArrayList<>();
        masters.add(masterService.findMasterByName(masterName));
        Order order = new Order(id, submissionDate, startDate, endDate, kindOfWork, cost,
                garageService.findGaragePlaceById(garageId, garagePlaceId), masters, orderStatus);
        return orderDao.addOrder(order);
    }

    @Override
    public boolean removeOrder(int id) {
        if (removeOrderOption) {
            Order order = findOrderById(id);
            return orderDao.removeOrder(order);
        } else {
            ConsoleHelper.writeMessage("Возможность удаления заказов отключена");
            return false;
        }
    }

    @Override
    public void cancelOrder(int id) {
        Order order = findOrderById(id);
        if (order != null) {
            orderDao.cancelOrder(order);
            ConsoleHelper.writeMessage(String.format("Заказ №%d отменен", id));
        } else {
            ConsoleHelper.writeMessage("При отмене заказа произошла ошибка");
        }

    }

    @Override
    public void closeOrder(int id) {
        Order order = findOrderById(id);
        if (order != null) {
            orderDao.closeOrder(order);
            ConsoleHelper.writeMessage(String.format("Заказ №%d закрыт", id));
        } else {
            ConsoleHelper.writeMessage("При закрытии заказа произошла ошибка");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getAllOrdersSorted(String sortBy) {
        List<Order> allOrdersSorted = new ArrayList<>();
        allOrdersSorted.addAll(orderDao.getAllOrders());

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            allOrdersSorted.sort(orderComparator);
        }
        return allOrdersSorted;
    }

    @Override
    public List<Order> getAllOrdersInProgress(String sortBy) {
        Comparator orderComparator = getOrderComparator(sortBy);
        return orderDao.getAllOrdersInProgress(orderComparator);
    }

    @Override
    public LocalDateTime getNearestFreeDate() {
        List<Order> allOrders = orderDao.getAllOrders();
        final LocalDateTime nearestFreeDate = allOrders.get(0).getEndDate();
        return allOrders.stream()
                .filter(order -> order.getEndDate().compareTo(nearestFreeDate) == -1)
                .findFirst().get().getEndDate();
    }

    @Override
    public List<Master> getMastersByOrder (int id) {
        Order order = findOrderById(id);
        try {
            return orderDao.getMastersByOrder(order);
        } catch (OrderNotFoundException e) {
            ConsoleHelper.writeMessage("Неправильный номер заказа");
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByPeriod (LocalDateTime startPeriod, LocalDateTime endPeriod, String sortBy) {
        List<Order> ordersByPeriod = new ArrayList<>();
        orderDao.getAllOrders().stream()
                .filter(order -> startPeriod.compareTo(order.getEndDate()) == -1 && endPeriod.compareTo(order.getEndDate()) == 1)
                .forEach(order -> ordersByPeriod.add(order));

        Comparator orderComparator = getOrderComparator(sortBy);
        if (orderComparator != null) {
            ordersByPeriod.sort(orderComparator);
        }
        return ordersByPeriod;
    }

    @Override
    public void updateOrderTime(Order order, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        orderDao.updateOrderTime(order, newStartTime, newEndTime);
    }

    @Override
    public void shiftEndTimeOrders(int hours, int minutes) {
        if (shiftEndTimeOrdersOption) {
            List<Order> allOrders = orderDao.getAllOrders();
            allOrders.stream().forEach(order -> {
                orderDao.updateOrderTime(order, order.getStartDate(), order.getEndDate().plusHours(hours).plusMinutes(minutes));
            });
        } else {
            ConsoleHelper.writeMessage("Возможность смещать время выполнения заказов отключена");
        }
    }

    @Override
    public Order findOrderById(int id) {
        for (Order order : getAllOrders()) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean importOrder(String fileName) {
        try {
            List<String> orderDataList = CsvUtil.importCsvFile(fileName);
            if (orderDataList == null) {
                throw new FileNotFoundException();
            }
            Order importOrder;
            GaragePlace importGaragePlace = garageService.findGaragePlaceById(Integer.parseInt(orderDataList.get(7)), Integer.parseInt(orderDataList.get(8)));
            List<Master> importMasters = new ArrayList<>();

            for (int i = 9; i < orderDataList.size(); i++) {
                importMasters.add(masterService.findMasterById(Integer.parseInt(orderDataList.get(i))));
            }
            importOrder = new Order(Integer.parseInt(orderDataList.get(0)), LocalDateTime.parse(orderDataList.get(1)),
                    LocalDateTime.parse(orderDataList.get(2)), LocalDateTime.parse(orderDataList.get(3)), orderDataList.get(4),
                    Integer.parseInt(orderDataList.get(5)), importGaragePlace, importMasters, OrderStatus.valueOf(orderDataList.get(6)));

            if (orderDao.getOrderById(importOrder.getId()) != null) {
                orderDao.updateOrder(importOrder);
                return true;
            } else {
                return orderDao.addOrder(importOrder);
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
    public boolean exportOrder(int id, String fileName) {
        Order orderToExport = orderDao.getOrderById(id);
        try {
            if (orderToExport != null) {
                return CsvUtil.exportCsvFile(toList(orderToExport), fileName);
            } else {
                ConsoleHelper.writeMessage("Неверный № заказа");
                return false;
            }
        } catch (WrongFileFormatException e) {
            ConsoleHelper.writeMessage("Неверный формат файла");
            return false;
        }
    }

    @Override
    public List<String> toList(Order order) {
        List<String> orderAsList = new ArrayList<>();
        orderAsList.add(String.valueOf(order.getId()));
        orderAsList.add(String.valueOf(order.getSubmissionDate()));
        orderAsList.add(String.valueOf(order.getStartDate()));
        orderAsList.add(String.valueOf(order.getEndDate()));
        orderAsList.add(order.getKindOfWork());
        orderAsList.add(String.valueOf(order.getCost()));
        orderAsList.add(order.getStatus().toString());
        orderAsList.add(String.valueOf(order.getGaragePlace().getGarageId()));
        orderAsList.add(String.valueOf(order.getGaragePlace().getId()));
        order.getMasters().stream()
                .forEach(master -> orderAsList.add(String.valueOf(master.getId())));
        return orderAsList;
    }

    @Override
    public void saveState() {
        SerializeUtil.saveState(getAllOrders(), "SerialsOrders.out");
    }

    @Override
    public void loadState() {
        orderDao.setAllOrders(SerializeUtil.loadState(Order.class, "SerialsOrders.out"));
    }

    private Comparator getOrderComparator(String sortBy) {
        Comparator orderComparator = null;
        switch (sortBy) {
            case "byCost":
                orderComparator = OrderByCostComparator.getInstance();
                break;
            case "byEndDate":
                orderComparator = OrderByEndDateComparator.getInstance();
                break;
            case "bySubmissionDate":
                orderComparator = OrderBySubmissionDateComparator.getInstance();
                break;
            case "byStartDate":
                orderComparator = OrderByStartDateComparator.getInstance();
                break;
        }
        return orderComparator;
    }

}