package ru.skillbox;

public enum Memory {
    BASIC("DDR3", 4, 0.0035),
    MIDDLE("DDR4", 8, 0.006),
    POWERFUL("DDR4", 16, 0.008),
    EXTREME("DDR4", 32, 0.009);

    private final String type;
    private final int capacity;
    private final double weight;

    Memory(String type, int capacity, double weight){
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "RAM: " + type + ", " + capacity + "Gb";
    }
}
