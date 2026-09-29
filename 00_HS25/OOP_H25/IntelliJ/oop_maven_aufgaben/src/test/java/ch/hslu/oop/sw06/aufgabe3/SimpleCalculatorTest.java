package ch.hslu.oop.sw06.aufgabe3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    private Calculator calculator;

    /**
     * Initialisiert das Testobjekt vor jedem Testfall.
     */
    @BeforeEach
    void setUp() {
        calculator = new SimpleCalculator();
    }

    @Test
    void testAdditionPositiveNumbers() {
        assertEquals(10,  calculator.addition(5, 5));
    }

    @Test
    void testAdditionNegativeNumbers() {
        assertEquals(5,  calculator.addition(10, -5));
    }

    @Test
    void testAdditionZeroNumbers() {
        assertEquals(10,  calculator.addition(10, 0));
    }
}