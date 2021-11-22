package ru.skillbox;

public enum Screen {
    LAPTOP_BASIC(15, "TN", 1.1),
    LAPTOP_MEDIUM(16, "IPS", 1.2),
    LAPTOP_LARGE(17, "IPS", 1.3),
    LAPTOP_GAME_SIZE(19, "IPS", 1.6),
    LAPTOP_MEDIA_SIZE(21, "IPS" , 1.9),
    MONITOR_SMALL(19, "TN", 2.6),
    MONITOR_MEDIUM(21, "TN", 2.9),
    MONITOR_LARGE(24, "IPS", 3.2),
    MONITOR_GAME_SIZE(27, "IPS", 3.6);

    private final int size;
    private final String type;
    private final double weight;

    Screen(int size, String type, double weight) {
        this.size = size;
        this.type = type;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Экран: " + size + "' " + type;
    }
}
