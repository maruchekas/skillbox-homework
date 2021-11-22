package ru.skillbox;

public enum HardDrive {
    GREEN_HDD("HDD", 1000, 0.85),
    BLUE_HDD("HDD", 2000, 0.9),
    RED_HDD("HDD", 4000, 0.9),
    BLACK_HDD("HDD", 8000, 0.95),
    BLUE_SSD("SSD", 512, 0.02),
    RED_SSD("SSD", 1000, 0.02);

    private final String typeDrive;
    private final int capacity;
    private final double weight;

    HardDrive(String typeDrive, int capacity, double weight){
        this.typeDrive = typeDrive;
        this.capacity = capacity;
        this.weight  = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return  typeDrive + " " +
                capacity +
                " Gb";
    }
}
