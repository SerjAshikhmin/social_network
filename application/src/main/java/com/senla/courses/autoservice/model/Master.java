package com.senla.courses.autoservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "Master")
public class Master implements Serializable {

    private static final long serialVersionUID = -4862926644813433703L;
    @Id
    private int id;
    private String name;
    private int category;
    private boolean busy;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    public Master() {
    }

    public Master(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = false;
    }

    public Master(int id, String name, int category, boolean busy, Order order) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = busy;
        this.order = order;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public int getOrderId() {
        return order.getId();
    }

    @Override
    public String toString() {
        String result = String.format("Master № %d, name: %s, category: %d, is %s busy", id, name, category, busy ? "" : "not");
        return result;
    }
}
