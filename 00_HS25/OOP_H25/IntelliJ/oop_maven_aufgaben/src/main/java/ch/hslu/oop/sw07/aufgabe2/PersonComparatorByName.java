package ch.hslu.oop.sw07.aufgabe2;

import java.util.Comparator;

public class PersonComparatorByName implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        int compareResult = p1.getLastName().compareTo(p2.getLastName()); //vergleicht die Nachnamen alphabetisch.
        if (compareResult == 0) {
            compareResult = p1.getFirstName().compareTo(p2.getFirstName());//falls nachname gleich, dann vorname
        }
        return compareResult; //Vergleichsergebnis
    }
}

