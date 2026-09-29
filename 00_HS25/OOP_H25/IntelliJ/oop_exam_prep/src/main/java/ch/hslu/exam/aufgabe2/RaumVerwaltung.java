package ch.hslu.exam.aufgabe2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaumVerwaltung {
    private List<Raum> raumListe;

    public RaumVerwaltung() {
        raumListe = new ArrayList<>();
        raumListe.add(new Raum(603, 12));
    }

    public void addRaum(Raum raum) {
        if (getRaumWithNumber(raum.getRaumNummer()) != null) {
            throw new IllegalStateException("Raum ist bereits verwaltet");
        }
        this.raumListe.add(raum);

        Collections.sort(this.raumListe);
    }

    public int getRaumNumber(int number) {
        for (Raum raum : this.raumListe) {
            if (raum.getRaumNummer() == number) {
                return raum.getRaumNummer();
            }
        }

        throw new IllegalStateException("Raum ist bereits verwaltet");
    }

    public Raum getRaumWithNumber(int number) {
        for (Raum raum : this.raumListe) {
            if (raum.getRaumNummer() == number) {
                return raum;
            }
        }

        return null;
    }

    public List<Raum> getRaumListe() {
        return Collections.unmodifiableList(this.raumListe);
    }
}
