package ch.hslu.oop.sw10.aufgabe2;

import ch.hslu.oop.sw09.aufgabe2.Temperatur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperaturVerlaufTest {
    @Test
    void testStatsWithOneValue() {
        TemperaturVerlauf verlauf = new TemperaturVerlauf();
        Temperatur t = Temperatur.createFromCelsius(5.5f);
        verlauf.add(t);

        assertEquals(1, verlauf.getCount());
        assertEquals(t, verlauf.getMaxTemperatur());
        assertEquals(t, verlauf.getMinTemperatur());
        assertEquals(t.getKelvin(), verlauf.getAvgTemperatur(), 0.01f);
    }

    @Test
    void testStatsWithMultipleValues() {
        TemperaturVerlauf verlauf = new TemperaturVerlauf();
        verlauf.add(Temperatur.createFromCelsius(0f));      // 273.15 K
        verlauf.add(Temperatur.createFromCelsius(20f));     // 293.15 K
        verlauf.add(Temperatur.createFromCelsius(100f));    // 373.15 K

        assertEquals(3, verlauf.getCount());
        assertEquals(Temperatur.createFromCelsius(0f), verlauf.getMinTemperatur());
        assertEquals(Temperatur.createFromCelsius(100f), verlauf.getMaxTemperatur());
        float expectedAvg = (273.15f + 293.15f + 373.15f) / 3.0f;
        assertEquals(expectedAvg, verlauf.getAvgTemperatur(), 0.01f);
    }

    @Test
    void testMinMaxEventFiring() {
        TemperaturVerlauf verlauf = new TemperaturVerlauf();
        final Temperatur[] minHolder = new Temperatur[1];
        final Temperatur[] maxHolder = new Temperatur[1];

        // Listener prüft Eventtypen und speichert Ergebnis
        verlauf.addListener(event -> {
            if (event.getTyp() == TemperaturEvent.Typ.MINIMUM) {
                minHolder[0] = event.getTemperatur();
            }
            if (event.getTyp() == TemperaturEvent.Typ.MAXIMUM) {
                maxHolder[0] = event.getTemperatur();
            }
        });

        Temperatur t1 = Temperatur.createFromCelsius(20f);    // Erstes: Min und Max
        verlauf.add(t1);
        assertEquals(t1, minHolder[0]);
        assertEquals(t1, maxHolder[0]);

        Temperatur t2 = Temperatur.createFromCelsius(10f);    // Neues Minimum
        verlauf.add(t2);
        assertEquals(t2, minHolder[0]);
        assertEquals(t1, maxHolder[0]);   // Maximum bleibt

        Temperatur t3 = Temperatur.createFromCelsius(30f);    // Neues Maximum
        verlauf.add(t3);
        assertEquals(t2, minHolder[0]);
        assertEquals(t3, maxHolder[0]);
    }

}