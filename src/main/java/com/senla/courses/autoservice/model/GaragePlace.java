package com.senla.courses.autoservice.model;

public class GaragePlace {

    private int id;
    private int garageId;
    private String type;
    private int area;
    private boolean busy;

    public GaragePlace(int id, int garageId, String type, int area) {
        this.id = id;
        this.garageId = garageId;
        this.type = type;
        this.area = area;
    }

    public GaragePlace(int id, int garageId, String type, int area, boolean busy) {
        this.id = id;
        this.garageId = garageId;
        this.type = type;
        this.area = area;
        this.busy = busy;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setArea(int area) {
        this.area = area;
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

    @Override
    public String toString() {
        String result = String.format("GaragePlace № %d, type: %s, area: %s, is %s busy", id, type, area, busy ? "" : "not");
        return result;
    }
}
