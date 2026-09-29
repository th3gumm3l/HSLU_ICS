package ch.hslu.oop.sw06.aufgabe2;

import ch.hslu.oop.sw06.aufgabe1.Maximum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumTest {

    @Test
    void maxLeftVarBigger() {
       assertEquals(5, new Maximum().max(5, 4));
    }

    @Test
    void maxRightVarBigger() {
        assertEquals(5, new Maximum().max(4, 5));
    }

    @Test
    void maxEqualVars() {
        assertEquals(5, new Maximum().max(5, 5));
    }
}