package com.senla.courses.autoservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "GaragePlace")
public class GaragePlace implements Serializable {

    private static final long serialVersionUID = -4862926644813433701L;
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
    private String type;
    private int area;
    private boolean busy;
    @OneToOne(mappedBy = "garagePlace", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Order order;

    public GaragePlace() {
    }

    public GaragePlace(int id, Garage garage, String type, int area) {
        this.id = id;
        this.garage = garage;
        this.type = type;
        this.area = area;
    }

    public GaragePlace(int id, Garage garage, String type, int area, boolean busy) {
        this.id = id;
        this.garage = garage;
        this.type = type;
        this.area = area;
        this.busy = busy;
    }

    public int getGarageId() {
        return this.garage.getId();
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getArea() {
        return area;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        String result = String.format("GaragePlace № %d, type: %s, area: %s, is %s busy", id, type, area, busy ? "" : "not");
        return result;
    }
}
