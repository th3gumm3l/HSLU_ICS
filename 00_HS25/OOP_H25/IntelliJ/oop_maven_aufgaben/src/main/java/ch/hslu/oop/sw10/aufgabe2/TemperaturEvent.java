package ch.hslu.oop.sw10.aufgabe2;

import ch.hslu.oop.sw09.aufgabe2.Temperatur;

import java.util.EventObject;

public class TemperaturEvent extends EventObject {
    public enum Typ { MINIMUM, MAXIMUM }
    private final Temperatur temperatur;
    private final Typ typ;

    public TemperaturEvent(Object source, Temperatur temperatur, Typ typ) {
        super(source);
        this.temperatur = temperatur;
        this.typ = typ;
    }

    public Temperatur getTemperatur() {
        return temperatur;
    }

    public Typ getTyp() {
        return typ;
    }
}