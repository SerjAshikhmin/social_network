package com.senla.courses.autoservice.model;

import com.senla.courses.autoservice.model.enums.OrderStatus;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class Order {

    private int id;
    private GregorianCalendar plannedStartDate;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String kindOfWork;
    private int cost;
    private GaragePlace garagePlace;
    private List<Master> masters;
    private OrderStatus status;

    public Order(int id, GregorianCalendar plannedStartDate, GregorianCalendar startDate, GregorianCalendar endDate, String kindOfWork, int cost,
                 GaragePlace garagePlace, List<Master> masters, OrderStatus status) {
        this.id = id;
        this.plannedStartDate = plannedStartDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kindOfWork = kindOfWork;
        this.cost = cost;
        this.garagePlace = garagePlace;
        this.masters = masters;
        this.status = status;
        this.garagePlace.setBusy(true);
        for (Master master : this.masters) {
            master.setCurrentOrder(this);
            master.setBusy(true);
        }
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public void setKindOfWork(String kindOfWork) {
        this.kindOfWork = kindOfWork;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setPlannedStartDate(GregorianCalendar plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public GregorianCalendar getPlannedStartDate() {
        return plannedStartDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public String getKindOfWork() {
        return kindOfWork;
    }

    public int getCost() {
        return cost;
    }

    public GaragePlace getGaragePlace() {
        return garagePlace;
    }

    public void setGaragePlace(GaragePlace garagePlace) {
        this.garagePlace = garagePlace;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy, HH:mm");
        String result = String.format("Order № %d, planned start date: %s; start date: %s; end date: %s; kind of work: %s," +
                " cost: %d, status: %s, garage place: %s; masters: %s", id, formatter.format(plannedStartDate.getTime()),
                formatter.format(startDate.getTime()), formatter.format(endDate.getTime()), kindOfWork, cost, status.toString(), garagePlace, masters);

        return result;
    }
}
