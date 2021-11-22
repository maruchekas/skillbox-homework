package ru.skillbox;

public class Car {

    private String manufacturer;
    private String model;
    private double gasTankVolume;
    private boolean isHybrid;

    public Car(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getGasTankVolume() {
        return gasTankVolume;
    }

    public void setGasTankVolume(double gasTankVolume) {
        this.gasTankVolume = gasTankVolume;
    }

    public boolean isHybrid() {
        return isHybrid;
    }

    public void setHybrid(boolean hybrid) {
        isHybrid = hybrid;
    }
}
