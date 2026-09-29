package ch.hslu.exam.aufgabe4;

import ch.hslu.exam.aufgabe2.RaumStatus;

import java.util.Objects;

public final class Raum implements Comparable<Raum> {
    private final int raumNummer;
    private final int kapazitaet;
    private RaumStatus status;

    Raum(int raumNummer, int kapazitaet) {
        if (raumNummer < 100 || raumNummer > 999) {
            throw new IllegalArgumentException("Die Raumnummer ist ausserhalb des Bereichs (100-999)");
        }
        if (kapazitaet <= 2) {
            throw new IllegalArgumentException("Die Kapazität ist zu klein");
        }
        this.raumNummer = raumNummer;
        this.kapazitaet = kapazitaet;
        this.status = RaumStatus.FREI;
    }

    public int getRaumNummer() {
        return raumNummer;
    }
    public int getKapazitaet() {
        return kapazitaet;
    }

    public RaumStatus getStatus() { return status; }
    void setStatus(RaumStatus status) { this.status = status; }

    public boolean isFrei() {
        if (status == RaumStatus.FREI) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Raum raum)) {
            return false;
        }
        final Raum other = (Raum) object;
        return (other.raumNummer == this.raumNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.raumNummer);
    }

    @Override
    public int compareTo(final Raum other) {
        return Integer.compare(this.raumNummer, other.raumNummer);
    }

    @Override
    public String toString() {
        return "Raum{" +
                "raumNummer=" + raumNummer +
                ", kapazitaet=" + kapazitaet +
                ", status=" + status +
                '}';
    }
}
