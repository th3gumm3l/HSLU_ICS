package ch.hslu.oop.sw10.aufgabe1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Engine implements Switchable {
    private int rpm;
    private MotorState state;
    private List<PropertyChangeListener> listeners;

    public Engine() {
        this.state = MotorState.OFF;
        this.rpm = 0;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void switchOn() {
        MotorState oldState = this.state;
        this.state = MotorState.ON;
        setRPM(1200);
        firePropertyChangeEvent(new PropertyChangeEvent(this, "state", oldState, this.state));
    }

    @Override
    public void switchOff() {
        MotorState oldState = this.state;
        this.state = MotorState.OFF;
        setRPM(0);
        firePropertyChangeEvent(new PropertyChangeEvent(this, "state", oldState, this.state));
    }

    @Override
    public boolean isSwitchedOn() {
        return this.state == MotorState.ON;
    }

    @Override
    public boolean isSwitchedOff() {
        return this.state == MotorState.OFF;
    }

    public int getRPM() {
        return rpm;
    }

    public void setRPM(int rpm) {
        this.rpm = rpm;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (this.listeners != null) {
            this.listeners.add(listener);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (this.listeners != null) {
            this.listeners.remove(listener);
        }
    }

    protected void firePropertyChangeEvent(PropertyChangeEvent pcEvent) {
        for (PropertyChangeListener listener : this.listeners) {
            listener.propertyChange(pcEvent);
        }
    }
}
