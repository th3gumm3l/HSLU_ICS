package ch.hslu.oop.sw08.aufgabe3;

import ch.hslu.oop.sw08.aufgabe1.Temperatur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementiert Tests für die Klasse Element
 */
class ElementTest {

    private Element element;
    private final static float KELVIN_OFFSET = 273.15f;

    @BeforeEach
    void setUp() {
        element = new Element("Blei", 1749f, 327.5f);
    }

    @Test
    void testAggregatszustandUnterSchmelzpunkt() {
        Temperatur temperatur = new Temperatur(250 + KELVIN_OFFSET); // 250°C in Kelvin

        assertEquals(Aggregatzustaende.SOLID, element.getAggregatZustand(temperatur));
    }

    @Test
    void testAggregatszustandZwischenSchmelzUndSiedepunkt() {
        Temperatur temperatur = new Temperatur(500 + KELVIN_OFFSET); // 500°C

        assertEquals(Aggregatzustaende.LIQUID, element.getAggregatZustand(temperatur));
    }

    @Test
    void testAggregatszustandUeberSiedepunkt() {
        Temperatur temperatur = new Temperatur(2000 + KELVIN_OFFSET); // 2000°C

        assertEquals(Aggregatzustaende.GAS, element.getAggregatZustand(temperatur));
    }

    @Test
    void testGrenzfallAmSchmelzpunkt() {
        Temperatur temperatur = new Temperatur(327.5f + KELVIN_OFFSET); // Genauer Schmelzpunkt

        assertEquals(Aggregatzustaende.LIQUID, element.getAggregatZustand(temperatur));
    }

    @Test
    void testGrenzfallAmSiedepunkt() {
        Temperatur temperatur = new Temperatur(1749f + KELVIN_OFFSET); // genau Siedepunkt

        assertEquals(Aggregatzustaende.GAS, element.getAggregatZustand(temperatur));
    }

}