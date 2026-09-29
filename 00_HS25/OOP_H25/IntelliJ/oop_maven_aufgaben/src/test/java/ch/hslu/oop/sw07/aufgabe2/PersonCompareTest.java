package ch.hslu.oop.sw07.aufgabe2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Collections;

public class PersonCompareTest {

    @Test
    void testCompareTo() {
        Person a = new Person(1, "Anna", "Müller");
        Person b = new Person(2, "Bernd", "Müller");
        Person c = new Person(3, "Clara", "Aebi");

        // c < a, weil "Aebi" vor "Müller"
        assertTrue(c.compareTo(a) < 0);

        // a < b, weil "Anna" vor "Bernd"
        assertTrue(a.compareTo(b) < 0);

        // b > a
        assertTrue(b.compareTo(a) > 0);
    }

    @Test
    void testSortierung() {
        List<Person> list = List.of(
                new Person(3, "Clara", "Aebi"),
                new Person(2, "Bernd", "Müller"),
                new Person(1, "Anna", "Müller")
        );

        List<Person> sorted = new java.util.ArrayList<>(list);
        Collections.sort(sorted);

        assertEquals("Aebi", sorted.get(0).getLastName());
        assertEquals("Müller", sorted.get(1).getLastName());
        assertEquals("Müller", sorted.get(2).getLastName());
        assertEquals("Anna", sorted.get(1).getFirstName());
        assertEquals("Bernd", sorted.get(2).getFirstName());
    }
}
