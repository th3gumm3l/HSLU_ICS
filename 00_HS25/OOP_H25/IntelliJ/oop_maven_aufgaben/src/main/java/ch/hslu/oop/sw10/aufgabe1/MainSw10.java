package ch.hslu.oop.sw10.aufgabe1;

public class MainSw10 {
    public static void main(String[] args) {
        Engine motor = new Engine();
        Car car = new Car("Honda Civic");

        motor.addPropertyChangeListener(car);

        motor.switchOn();
        motor.switchOff();
    }
}
