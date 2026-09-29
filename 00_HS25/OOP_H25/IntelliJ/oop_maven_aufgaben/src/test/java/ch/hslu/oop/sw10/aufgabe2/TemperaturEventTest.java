package ch.hslu.oop.sw10.aufgabe2;

import ch.hslu.oop.sw09.aufgabe2.Temperatur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperaturEventTest {
    @Test
    void testEventConstruction() {
        Temperatur t = Temperatur.createFromCelsius(23.5f);
        TemperaturEvent e = new TemperaturEvent(this, t, TemperaturEvent.Typ.MAXIMUM);

        assertEquals(t, e.getTemperatur());
        assertEquals(TemperaturEvent.Typ.MAXIMUM, e.getTyp());
        assertEquals(this, e.getSource());
    }

    @Test
    void testEventMinimumType() {
        Temperatur t = Temperatur.createFromCelsius(-10f);
        TemperaturEvent e = new TemperaturEvent(this, t, TemperaturEvent.Typ.MINIMUM);

        assertEquals(TemperaturEvent.Typ.MINIMUM, e.getTyp());
    }

}