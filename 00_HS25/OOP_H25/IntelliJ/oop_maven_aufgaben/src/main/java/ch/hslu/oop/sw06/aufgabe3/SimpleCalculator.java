package ch.hslu.oop.sw06.aufgabe3;

public class SimpleCalculator implements Calculator{

    @Override
    public int addition(int summand1, int summand2) {
        return Math.addExact(summand1, summand2);
    }
}
