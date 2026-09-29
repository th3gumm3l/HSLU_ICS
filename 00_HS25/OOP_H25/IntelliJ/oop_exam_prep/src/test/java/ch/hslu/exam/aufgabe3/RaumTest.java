package ch.hslu.exam.aufgabe3;

import ch.hslu.exam.aufgabe2.RaumStatus;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RaumTest {
    @Test
    void testEquals() {
        EqualsVerifier.forClass(Raum.class)
                .withOnlyTheseFields("raumNummer")
                .verify();
    }

    @Test
    void testIsFreiTrue(){
        Raum raum = new Raum(200, 20);

        assertTrue(raum.isFrei());
    }

    @Test
    void testIsFreiFalse(){
        Raum raum = new Raum(200, 20);
        raum.setStatus(RaumStatus.BELEGT);

        assertFalse(raum.isFrei());
    }
}