package ch.hslu.exam.aufgabe2;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Raum raum)) return false;
        return getRaumNummer() == raum.getRaumNummer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.raumNummer);
    }

    @Override
    public int compareTo(final Raum other) {
        return Integer.compare(this.raumNummer, other.raumNummer);
    }
}
