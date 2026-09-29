package ch.hslu.oop.sw08.aufgabe1;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementiert Tests für die Klasse Temperatur
 */
class TemperaturTest {
    private Temperatur temperatur;

    @BeforeEach
    void setUp() {
        temperatur = new Temperatur(5.0f);
    }

    @Test
    void getKelvin() {
        assertEquals(5.0f, temperatur.getKelvin(), 0);
    }

    @Test
    void setKelvin() {
        temperatur.setKelvin(5.0f);
        assertEquals(5.0f, temperatur.getKelvin(), 0);
    }

    @Test
    void getCelsius() {
        assertEquals(-268.15f, temperatur.getCelsius(), 0);
    }

    @Test
    void setCelsius() {
        temperatur.setCelsius(5.0f);
        assertEquals(5.0f, temperatur.getCelsius(), 0);
    }

    @Test
    void testEquals() {
        EqualsVerifier.forClass(Temperatur.class).verify();
    }

    @Test
    void testHashCode() {
        assertEquals(temperatur.hashCode(), temperatur.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(temperatur.toString(), temperatur.toString());
    }

    @Test
    void testCompareTo_Smaller() {
        Temperatur t1 = new Temperatur(273.15f); // 0°C
        Temperatur t2 = new Temperatur(300.15f); // 27°C
        assertTrue(t1.compareTo(t2) < 0, "t1 sollte kleiner sein als t2");
    }

    @Test
    void testCompareTo_Equal() {
        Temperatur t1 = new Temperatur(273.15f);
        Temperatur t2 = new Temperatur(273.15f);
        assertEquals(0, t1.compareTo(t2), "Beide Temperaturen sind gleich");
    }

    @Test
    void testCompareTo_Greater() {
        Temperatur t1 = new Temperatur(310.15f); // 37°C
        Temperatur t2 = new Temperatur(273.15f); // 0°C
        assertTrue(t1.compareTo(t2) > 0, "t1 sollte groesser sein als t2");
    }

    @Test
    void convertKelvinToCelsius() {
        assertEquals(25.0f, Temperatur.convertKelvinToCelsius(298.15f), 0.01f);
    }

    @Test
    void convertCelsiusToKelvin() {
        assertEquals(298.15f, Temperatur.convertCelsiusToKelvin(25.0f), 0.01f);
    }
}