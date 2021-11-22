package ru.skillbox;

public class Dimensions {

    private final int heightCargo;
    private final int widthCargo;
    private final int longCargo;

    public Dimensions(int heightCargo, int widthCargo, int longCargo) {
        this.heightCargo = heightCargo;
        this.widthCargo = widthCargo;
        this.longCargo = longCargo;
    }

    public int calculateVolume(){
        return getHeightCargo() * getWidthCargo() * getLongCargo();
    }

    public int getHeightCargo() {
        return heightCargo;
    }

    public Dimensions setHeightCargo(int heightCargo) {
        return new Dimensions(heightCargo, widthCargo, longCargo);
    }

    public int getWidthCargo() {
        return widthCargo;
    }

    public Dimensions setWidthCargo(int widthCargo) {
        return new Dimensions(heightCargo, widthCargo, longCargo);
    }

    public int getLongCargo() {
        return longCargo;
    }

    public Dimensions setLongCargo(int longCargo) {
        return new Dimensions(heightCargo, widthCargo, longCargo);
    }

    @Override
    public String toString() {
        return "" + calculateVolume();
    }
}
