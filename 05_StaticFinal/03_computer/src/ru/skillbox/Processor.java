package ru.skillbox;

public enum Processor {
    I3(2200, 2, "Intel", 0.052),
    I5(2600, 4, "Intel", 0.06),
    I7(3200, 8, "Intel", 0.065),
    I9(3700, 8, "Intel", 0.075),
    RYZEN_3(2600, 4, "AMD", 0.06),
    RYZEN_5(2900, 4, "AMD", 0.065),
    RYZEN_7(3400, 8, "AMD", 0.07);

    private final String vendor;
    private final int baseClock;
    private final int numOfCores;
    private final double weight;

    Processor(int baseClock, int numOfCores, String vendor, double weight) {
        this.vendor = vendor;
        this.baseClock = baseClock;
        this.numOfCores = numOfCores;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "CPU: " + vendor + " " + this.name() + " " + numOfCores + " cores, " + + baseClock + " MHz ";
    }
}
