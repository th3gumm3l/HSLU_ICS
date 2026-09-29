package ch.hslu.oop.sw09.aufgabe2;

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
        temperatur = Temperatur.createFromKelvin(5.0f);
    }

    @Test
    void getKelvin() {
        assertEquals(5.0f, temperatur.getKelvin(), 0);
    }

    @Test
    void getCelsius() {
        assertEquals(-268.15f, temperatur.getCelsius(), 0);
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
    void convertKelvinToCelsius() {
        assertEquals(25.0f, Temperatur.convertKelvinToCelsius(298.15f), 0.01f);
    }

    @Test
    void convertCelsiusToKelvin() {
        assertEquals(298.15f, Temperatur.convertCelsiusToKelvin(25.0f), 0.01f);
    }

    @Test
    void testCreateFromKelvin_Valid() {
        Temperatur t = Temperatur.createFromKelvin(300.0f);
        assertEquals(300.0f, t.getKelvin());
    }

    @Test
    void testCreateFromKelvin_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Temperatur.createFromKelvin(-1.0f);
        });
        assertEquals("Temperaturwert darf nicht negativ sein.", exception.getMessage());
    }

    @Test
    void testCreateFromCelsius_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Temperatur.createFromCelsius(-300.0f); // Ergibt < 0 Kelvin
        });
        assertEquals("Temperaturwert darf nicht negativ sein.", exception.getMessage());
    }
}