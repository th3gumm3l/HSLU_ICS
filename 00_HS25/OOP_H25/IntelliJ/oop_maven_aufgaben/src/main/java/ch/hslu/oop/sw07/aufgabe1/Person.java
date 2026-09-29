package ch.hslu.oop.sw07.aufgabe1;

import java.util.Objects;

public final class Person {
    private final long ID;
    private final String lastName;
    private final String firstName;

    public Person(long ID, String lastName, String firstName) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getID() {
        return ID;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return getID() == person.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID());
    }
}
