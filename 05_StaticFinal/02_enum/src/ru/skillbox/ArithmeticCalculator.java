package ru.skillbox;

public class ArithmeticCalculator {

    private int a;
    private int b;

    ArithmeticCalculator(int a, int b){
        this.a = a;
        this.b = b;
    }

    public void calculate(Operation operation){
        if(operation.equals(Operation.ADD)){
            System.out.println(a + b);
        }
        if (operation.equals(Operation.SUBTRACT)){
            System.out.println(a - b);
        }
        if(operation.equals(Operation.MULTIPLY)){
            System.out.println(a * b);
        }
    }
}
