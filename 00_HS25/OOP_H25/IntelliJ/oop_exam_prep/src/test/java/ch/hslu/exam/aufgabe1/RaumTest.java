package ch.hslu.exam.aufgabe1;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RaumTest {

    @Test
    void getObject() {
        final int raumnummer = 100;
        final int kapazitaet = 200;

        final Raum raum = new Raum(raumnummer, kapazitaet);
        assertEquals(raumnummer, raum.getRaumNummer());
        assertEquals(kapazitaet, raum.getKapazitaet());
    }

    @Test
    void raumNummerToHigh(){
        final int raumnummer = 1000;
        assertThrows(IllegalArgumentException.class, () -> {
            new Raum(raumnummer, 100);
        });
    }

    @Test
    void raumNummerToLow(){
        final int raumnummer = 99;
        assertThrows(IllegalArgumentException.class, () -> {
            new Raum(raumnummer, 100);
        });
    }

    @Test
    void kapazitaetToLow(){
        final int kapazitaet = 1;
        assertThrows(IllegalArgumentException.class, () -> {
            new Raum(200, kapazitaet);
        });
    }

    @Test
    void testEquals() {
        EqualsVerifier.forClass(Raum.class)
                .withOnlyTheseFields("raumNummer")
                .verify();
    }
}