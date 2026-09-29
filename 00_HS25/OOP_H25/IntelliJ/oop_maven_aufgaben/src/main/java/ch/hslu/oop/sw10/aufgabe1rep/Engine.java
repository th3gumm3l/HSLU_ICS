package ch.hslu.oop.sw10.aufgabe1rep;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Engine implements Switchable {
    private int rpm;
    private boolean turnedOn;

    /*
    Konstruktor
     */
    public Engine() {
        this.turnedOn = false;
        setRPM(0);
    }

    /*
    Get-Setter
     */
    public int getRPM() {
        return rpm;
    }

    private void setRPM(int rpm) {
        this.rpm = rpm;
    }

    /*
    Event-Handling Methoden
     */
    private final List<PropertyChangeListener> changeListeners = new ArrayList<>();

    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changeListeners.add(listener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changeListeners.remove(listener);
    }

    private void firePropertyChangeEvent(final PropertyChangeEvent pcEvent) {
        for (final PropertyChangeListener listener : this.changeListeners) {
            listener.propertyChange(pcEvent);
        }
    }

    /*
    Switchable Methoden
     */
    @Override
    public void switchOn() {
        if (isSwitchedOff()){
            this.turnedOn = true;
            setRPM(1200);
            final PropertyChangeEvent pcEvent =
                    new PropertyChangeEvent(this, "Status", State.OFF, State.ON);
            this.firePropertyChangeEvent(pcEvent);
        }
    }

    @Override
    public void switchOff() {
        if (isSwitchedOn()){
            this.turnedOn = false;
            setRPM(0);
            final PropertyChangeEvent pcEvent =
                    new PropertyChangeEvent(this, "Status", State.ON, State.OFF);
            this.firePropertyChangeEvent(pcEvent);
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return this.turnedOn;
    }

    @Override
    public boolean isSwitchedOff() {
        return !this.turnedOn;
    }
}
