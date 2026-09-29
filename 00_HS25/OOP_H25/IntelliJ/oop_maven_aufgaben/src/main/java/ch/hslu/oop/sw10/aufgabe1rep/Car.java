package ch.hslu.oop.sw10.aufgabe1rep;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class Car implements Switchable {
    private final String model;
    private boolean running;
    private final Light lightFrontLeft;
    private final Light lightFrontRight;
    private final Engine engine;
    private static final Logger LOGGER = Logger.getLogger(Car.class.getName());

    /*
    Innere Klassen für Event-Handling
     */
    private class EnginePropertyListener implements PropertyChangeListener {
        @Override
        public void propertyChange(final PropertyChangeEvent event){
            handleEngineEvent(event);
        }

        private void handleEngineEvent(PropertyChangeEvent event) {
            LOGGER.info("Event vom Motor: " + getModel() + "| Status-Wechsel von " + event.getOldValue() + " zu " + event.getNewValue());
        }
    }

    private class LightPropertyListener implements PropertyChangeListener {
        @Override
        public void propertyChange(final PropertyChangeEvent event){
            handleLightEvent(event);
        }

        private void handleLightEvent(PropertyChangeEvent event) {
            LOGGER.info("Event vom Scheinwerfer: " + getModel() + "| Status-Wechsel von " + event.getOldValue() + " zu " + event.getNewValue());
        }
    }

    /*
    Konstruktor
     */
    public Car(String model) {
        this.model = model;
        this.running = false;
        this.lightFrontLeft = new Light();
        this.lightFrontLeft.addPropertyChangeListener(new Car.LightPropertyListener());
        this.lightFrontRight = new Light();
        this.lightFrontRight.addPropertyChangeListener(new Car.LightPropertyListener());
        this.engine = new Engine();
        this.engine.addPropertyChangeListener(new Car.EnginePropertyListener());
    }

    /*
    Get-Setter
     */
    public String getModel() {
        return model;
    }

    /*
    Switchable Methoden
     */
    @Override
    public void switchOn() {
        if (isSwitchedOff()){
            this.running = true;
            this.lightFrontLeft.switchOn();
            this.lightFrontRight.switchOn();
            this.engine.switchOn();
        }
    }

    @Override
    public void switchOff() {
        if (isSwitchedOn()){
            this.running = false;
            this.lightFrontLeft.switchOff();
            this.lightFrontRight.switchOff();
            this.engine.switchOff();
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return this.running;
    }

    @Override
    public boolean isSwitchedOff() {
        return !this.running;
    }
}
