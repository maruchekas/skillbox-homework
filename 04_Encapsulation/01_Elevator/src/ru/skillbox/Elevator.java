package ru.skillbox;

public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveUp() {
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
    }

    public void moveDown() {
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
    }

    public void move(int floor) {
        if (floor >= minFloor && floor <= maxFloor) {
            while (currentFloor != floor) {
                if (currentFloor > floor) {
                    moveDown();
                    System.out.println(currentFloor);
                } else {
                    moveUp();
                    System.out.println(currentFloor);
                }
            }
        } else System.out.println("There is no such floor in this building :(. You can drive about from " + minFloor + " floor to " + maxFloor + " floor");
    }
}
