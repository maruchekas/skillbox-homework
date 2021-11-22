package ru.skillbox;

public class Country {

    private String countryName;
    private long countryPopulation;
    private double countryArea;
    private String countryCapital;
    private boolean isSeaCoast;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public double getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(double countryArea) {
        this.countryArea = countryArea;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public boolean isSeaCoast() {
        return isSeaCoast;
    }

    public void setSeaCoast(boolean seaCoast) {
        isSeaCoast = seaCoast;
    }
}
