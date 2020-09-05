package com.senla.courses.autoservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Garage")
public class Garage implements Serializable {

    private static final long serialVersionUID = -4862926644813433702L;
    @Id
    private int id;
    private String address;
    @OneToMany(mappedBy = "garage", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<GaragePlace> garagePlaces;

    public Garage() {
    }

    public Garage(int id, String address, List<GaragePlace> garagePlaces) {
        this.id = id;
        this.address = address;
        this.garagePlaces = garagePlaces;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public List<GaragePlace> getGaragePlaces() {
        return garagePlaces;
    }

    @Override
    public String toString() {
        String result = String.format("Garage № %d, address: %s, garagePlaces: ", id, address);
        return result + garagePlaces;
    }
}
