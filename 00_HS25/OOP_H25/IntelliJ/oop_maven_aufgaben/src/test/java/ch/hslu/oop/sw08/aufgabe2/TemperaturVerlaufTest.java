package ch.hslu.oop.sw08.aufgabe2;

import ch.hslu.oop.sw08.aufgabe1.Temperatur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementiert Tests für die Klasse Temperatur-Verlauf
 */
class TemperaturVerlaufTest {

    private TemperaturVerlauf temperaturVerlauf;

    @BeforeEach
    void setUp() {
        temperaturVerlauf = new TemperaturVerlauf();

        temperaturVerlauf.add(new  Temperatur(30));
        temperaturVerlauf.add(new  Temperatur(40));
        temperaturVerlauf.add(new  Temperatur(50));
    }
    @Test
    void getCount() {
        assertEquals(3, temperaturVerlauf.getCount());
    }

    @Test
    void getCountEmptyList() {
        temperaturVerlauf.clear();
        assertEquals(0, temperaturVerlauf.getCount());
    }

    @Test
    void add() {
        temperaturVerlauf.add(new  Temperatur(60));
        assertEquals(4, temperaturVerlauf.getCount());
    }

    @Test
    void clear() {
        temperaturVerlauf.clear();
        assertEquals(0, temperaturVerlauf.getCount());
    }

    @Test
    void getMaxTemperatur() {
        Temperatur maxTemperatur = temperaturVerlauf.getMaxTemperatur();

        assertEquals(50, maxTemperatur.getKelvin());
    }

    @Test
    void getMaxTemperaturEmptyList() {
        temperaturVerlauf.clear();
        assertNull(temperaturVerlauf.getMaxTemperatur());
    }

    @Test
    void getMinTemperatur() {
        Temperatur minTemperatur = temperaturVerlauf.getMinTemperatur();

        assertEquals(30, minTemperatur.getKelvin());
    }

    @Test
    void getMinTemperaturEmptyList() {
        temperaturVerlauf.clear();
        assertNull(temperaturVerlauf.getMinTemperatur());
    }

    @Test
    void getAvgTemperatur() {
        float avgTemperatur = temperaturVerlauf.getAvgTemperatur();
        assertEquals(40, avgTemperatur);
    }

    @Test
    void getAvgTemperaturEmptyList() {
        temperaturVerlauf.clear();
        assertTrue(Float.isNaN(temperaturVerlauf.getAvgTemperatur()));
    }
}