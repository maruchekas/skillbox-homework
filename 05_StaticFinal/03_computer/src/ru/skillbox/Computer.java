package ru.skillbox;

public class Computer {
    private final String vendor;
    private final String name;
    private Processor processor;
    private Memory memory;
    private HardDrive hardDrive;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(String vendor, String name,
                    Processor processor, Memory memory,
                    HardDrive hardDrive, Screen screen, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public double getPCWeight(){
        return processor.getWeight()
                + memory.getWeight()
                + hardDrive.getWeight()
                + screen.getWeight()
                + keyboard.getWeight();
    }

    @Override
    public String toString() {
        return "Ваш компьютер: " + vendor +
                " - " + name + "\n" +
                processor + "," + "\n" +
                memory + "," + "\n" +
                hardDrive + "\n" +
                screen + "\n" +
                keyboard + "\n";
    }
}
