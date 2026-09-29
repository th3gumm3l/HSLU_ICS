package ch.hslu.exam.aufgabe5;

import java.util.Objects;

public final class Raum {

    private final int raumnummer;
    private final int kapazitaet;
    private RaumStatus status;

    Raum(final int raumnummer, final int kapazitaet) {
        if (raumnummer < 100 || raumnummer > 999) {
            throw new IllegalArgumentException("Raumnummer muss zwischen 100 und 999 liegen. Gegeben: " + raumnummer);
        }
        if (kapazitaet <= 2) {
            throw new IllegalArgumentException("Kapazität muss grösser als 2 sein. Gegeben: " + kapazitaet);
        }
        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
        this.status = RaumStatus.FREI;
    }

    public int getRaumnummer() {
        return raumnummer;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public RaumStatus getStatus() {
        return status;
    }

    void setStatus(final RaumStatus status) {
        this.status = status;
    }

    public boolean isFrei() {
        return this.status == RaumStatus.FREI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Raum raum = (Raum) o;
        return raumnummer == raum.raumnummer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(raumnummer);
    }

    @Override
    public String toString() {
        return "Raum{" +
                "Nr=" + raumnummer +
                ", Kap=" + kapazitaet +
                ", Status=" + status +
                '}';
    }
}
