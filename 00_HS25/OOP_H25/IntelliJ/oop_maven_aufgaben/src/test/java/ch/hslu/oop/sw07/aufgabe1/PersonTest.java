package ch.hslu.oop.sw07.aufgabe1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import nl.jqno.equalsverifier.*;

class PersonTest {

    @Test
    void testKonstruktor() {
        Person p = new Person(1L, "Müller", "Anna");
        assertEquals(1L, p.getID());
        assertEquals("Müller", p.getLastName());
        assertEquals("Anna", p.getFirstName());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Person.class)
                .withOnlyTheseFields("ID")
                .verify();
    }

    @Test
    void testEqualsIdentity() {
        Person p1 = new Person(1L, "Meier", "Hans");
        Person p2 = p1;
        assertTrue(p1.equals(p2));
    }

    @Test
    void testEqualsValue(){
        Person p1 = new Person(1L, "Meier", "Hans");
        Person p2 = new Person(1L, "Meier", "Hans");
        assertTrue(p1.equals(p2));
    }

    @Test
    void testEqualsFalse(){
        Person p1 = new Person(1L, "Meier", "Hans");
        Person p2 = new Person(2L, "Meier", "Hans");
        assertFalse(p1.equals(p2));
    }
}