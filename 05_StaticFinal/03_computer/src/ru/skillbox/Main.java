package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        Computer pc1 = new Computer("ASUS", "ROG",
                Processor.I7, Memory.MIDDLE, HardDrive.BLUE_HDD, Screen.LAPTOP_LARGE, Keyboard.GAME);

        Computer pc2 = new Computer("Lenovo", "ThinkPad",
                Processor.RYZEN_5, Memory.BASIC, HardDrive.BLUE_SSD, Screen.LAPTOP_BASIC, Keyboard.OFFICE);

        Computer pc3 = new Computer("ALIENWARE", "M15",
                Processor.I9, Memory.EXTREME, HardDrive.RED_SSD, Screen.LAPTOP_GAME_SIZE, Keyboard.GAME);
        System.out.println(pc1);
        System.out.println(pc2);
        System.out.println(pc3);
        System.out.println(pc1.getPCWeight());
        System.out.println(pc2.getPCWeight());
        System.out.println(pc3.getPCWeight());
    }
}
