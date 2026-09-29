package ch.hslu.oop.sw07.aufgabe2;

import java.util.Objects;

public final class Person implements Comparable<Person> {
    private final long ID;
    private final String firstName;
    private final String lastName;

    public Person(long ID, String firstName, String lastName) {
        this.ID = ID;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public long getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        return (object instanceof Person person)
                && (ID == person.ID);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(ID);
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        return Long.compare(this.ID, other.ID);
    }
}
