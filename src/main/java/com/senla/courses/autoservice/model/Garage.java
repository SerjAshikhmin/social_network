package com.senla.courses.autoservice.model;

import java.util.List;

public class Garage {

    private int id;
    private String address;
    private List<GaragePlace> garagePlaces;

    public Garage(int id, String address, List<GaragePlace> garagePlaces) {
        this.id = id;
        this.address = address;
        this.garagePlaces = garagePlaces;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setGaragePlaces(List<GaragePlace> garagePlaces) {
        this.garagePlaces = garagePlaces;
    }

    @Override
    public String toString() {
        String result = String.format("Garage № %d, address: %s, garagePlaces: ", id, address);
        return result + garagePlaces;
    }
}
