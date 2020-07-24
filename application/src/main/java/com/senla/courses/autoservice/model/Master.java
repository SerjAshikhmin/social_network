package com.senla.courses.autoservice.model;

import java.io.Serializable;

public class Master implements Serializable {

    private static final long serialVersionUID = -4862926644813433703L;
    private int id;
    private String name;
    private int category;
    private boolean busy;
    private Order currentOrder;

    public Master(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Master(int id, String name, int category, boolean busy, Order currentOrder) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = busy;
        this.currentOrder = currentOrder;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = String.format("Master № %d, name: %s, category: %d, is %s busy", id, name, category, busy ? "" : "not");
        return result;
    }
}
