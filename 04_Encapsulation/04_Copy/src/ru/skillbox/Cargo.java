package ru.skillbox;

public class Cargo {

    private final Dimensions volumeCargo;
    private final double weightCargo;
    private final String address;
    private final boolean canBeFlipOver;
    private final boolean isFragile;
    private final String registerNumber;

    public Cargo(Dimensions volumeCargo, double weightCargo, String address,
                 boolean canBeFlipOver, boolean isFragile, String registerNumber) {
        this.volumeCargo = volumeCargo;
        this.weightCargo = weightCargo;
        this.address = address;
        this.canBeFlipOver = canBeFlipOver;
        this.isFragile = isFragile;
        this.registerNumber = registerNumber;
    }

    public Dimensions getVolumeCargo() {
        return volumeCargo;
    }

    public Cargo setVolumeCargo(Dimensions volumeCargo) {
        return new Cargo(volumeCargo, weightCargo, address, canBeFlipOver, isFragile, registerNumber);
    }

    public double getWeightCargo() {
        return weightCargo;
    }

    public Cargo setWeightCargo(int weightCargo) {
        return new Cargo(volumeCargo, weightCargo, address, canBeFlipOver, isFragile, registerNumber);
    }

    public String getAddress() {
        return address;
    }

    public Cargo setAddress(String address) {
        return new Cargo(volumeCargo, weightCargo, address, canBeFlipOver, isFragile, registerNumber);
    }

    public boolean getIsCanBeFlipOver() {
        return canBeFlipOver;
    }

    public boolean getIsFragile() {
        return isFragile;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    @Override
    public String toString() {
        return "Информация о грузе" + "\n" +
                "Объем: " + volumeCargo + " м3" + "\n" +
                "Вес: " + weightCargo + " кг" + "\n" +
                "Адрес: " + address + "\n" +
                "Можно ли переворачивать груз: " + (canBeFlipOver? "Да" : "Нет") + "\n" +
                "Хрупкий груз: " + (isFragile ? "Да" : "Нет") + "\n" +
                "Регистрационный номер: " + registerNumber;
    }
}
