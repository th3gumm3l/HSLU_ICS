package ch.hslu.oop.sw07.aufgabe2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonComparatorByNameTest {

    @Test
    void testNachnameKleiner() {
        Person p1 = new Person(3452, "Adler", "Sandra");
        Person p2 = new Person(435432, "Imeri", "Berna");

        PersonComparatorByName comp = new PersonComparatorByName();
        assertTrue(comp.compare(p1, p2) < 0, "Adler < Imeri alphabetisch");
    }


    @Test
    void testNachnameGrösser() {
        Person p1 = new Person(3452, "Adler", "Berna");
        Person p2 = new Person(435432, "Imeri", "Sandra");

        PersonComparatorByName comp = new PersonComparatorByName();
        assertTrue(comp.compare(p1, p2) < 0);

    }

    @Test
    void testNachnameGleich() {
        Person p1 = new Person(3452, "Imeri", "Berna");
        Person p2 = new Person(435432, "Imeri", "Sandra");

        PersonComparatorByName comp = new PersonComparatorByName();
        assertTrue(comp.compare(p1, p2) < 0, "Berna < Sandra alphabetisch");
    }

    @Test
    void testPersonSorting() {
        List<Person> personen = List.of(
                new Person(546543, "Anna", "Meier"),
                new Person(2343, "David", "Becker"),
                new Person(78768, "Clara", "Schmidt"),
                new Person(567, "Bernd", "Meier")
        );

        List<Person> erwarteteReihenfolge = List.of(
                new Person(2343, "David", "Becker"),
                new Person(546543, "Anna", "Meier"),
                new Person(567, "Bernd", "Meier"),
                new Person(78768, "Clara", "Schmidt")
        );

        personen = new ArrayList<>(personen); // List.sort benötigt modifizierbare Liste
        personen.sort(new PersonComparatorByName()); // mit comparator vergleichen

        assertEquals(erwarteteReihenfolge, personen); //prüfung
    }

}