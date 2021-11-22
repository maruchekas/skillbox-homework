package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        Dimensions dimensions = new Dimensions(3, 4, 3);
        Cargo cargo = new Cargo(dimensions, 9.6, "Mayakovskogo Street", false, true, "221ABC100");
        System.out.println(cargo);
    }
}
