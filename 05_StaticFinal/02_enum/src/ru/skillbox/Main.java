package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator calc = new ArithmeticCalculator(48, 86);
        calc.calculate(Operation.SUBTRACT);
        calc.calculate(Operation.ADD);
        calc.calculate(Operation.MULTIPLY);
    }
}
