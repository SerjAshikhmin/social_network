package com.senla.courses.autoservice.model;

import java.io.Serializable;

public class Master implements Serializable {

    private static final long serialVersionUID = -4862926644813433703L;
    private int id;
    private String name;
    private int category;
    private boolean busy;
    private int orderId;

    public Master(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = false;
        this.orderId = 0;
    }

    public Master(int id, String name, int category, boolean busy, int orderId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = busy;
        this.orderId = orderId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
