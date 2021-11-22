package ru.skillbox;

public enum Keyboard {
    OFFICE("ergonomic", false, 0.45),
    HOME("slim, ergonomic", false, 0.55),
    MULTIMEDIA("slim, media", true, 0.45),
    GAME("slim, ergonomic", true, 0.65),
    PROFESSIONAL("mechanical", false, 0.9);

    private final String type;
    private final boolean isLight;
    private final double weight;

    Keyboard(String type, boolean isLight, double weight) {
        this.type = type;
        this.isLight = isLight;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Клавиатура: " + type + ", " + (isLight ? "с подсветкой" : "без подсветки");
    }
}
