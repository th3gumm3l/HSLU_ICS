package ch.hslu.oop.sw10.aufgabe1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class Car implements Switchable, PropertyChangeListener {
    private final String model;
    private boolean running;
    private final Light lightFrontLeft;
    private final Light lightFrontRight;
    private final Engine engine;
    private static final Logger LOGGER = Logger.getLogger(Car.class.getName());

    public Car(String model) {
        this.model = model;
        this.running = false;
        this.lightFrontLeft = new Light();
        this.lightFrontRight = new Light();
        this.engine = new Engine();
    }

    public String getModel() {
        return model;
    }

    @Override
    public void switchOn() {
        this.running = true;
        this.lightFrontLeft.switchOn();
        this.lightFrontRight.switchOn();
        this.engine.switchOn();
    }

    @Override
    public void switchOff() {
        this.running = false;
        this.lightFrontLeft.switchOff();
        this.lightFrontRight.switchOff();
        this.engine.switchOff();
    }

    @Override
    public boolean isSwitchedOn() {
        return this.running;
    }

    @Override
    public boolean isSwitchedOff() {
        return this.running;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        LOGGER.info( getModel() + " hat eine Statusänderung registriert: "
                + event.getPropertyName() + " von " + event.getOldValue() + " zu " + event.getNewValue());
    }
}
