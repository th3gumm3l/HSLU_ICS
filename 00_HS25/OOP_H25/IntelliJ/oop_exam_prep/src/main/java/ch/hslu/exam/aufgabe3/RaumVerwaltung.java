package ch.hslu.exam.aufgabe3;

import ch.hslu.exam.aufgabe2.RaumStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaumVerwaltung {
    private List<Raum> raumListe;

    public RaumVerwaltung() {
        raumListe = new ArrayList<>();
        raumListe.add(new Raum(603, 12));
    }

    public Raum bucheRaum(final int anzahlPers){
        if (anzahlPers < 1){
            throw new IllegalArgumentException("Zu kleine Zahl mitgegeben");
        }

        for (Raum raum : raumListe) {
            boolean istFrei = raum.isFrei();
            boolean genugPlatz = raum.getKapazitaet() >= anzahlPers;

            if (istFrei && genugPlatz){
                raum.setStatus(RaumStatus.BELEGT);
                return raum;
            }
        }

        return null;
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

    public boolean raumFreigeben(final int raumNummer) {
        // 1. Raum suchen
        final Raum raum = this.getRaumWithNumber(raumNummer);

        // 2. Prüfung: Existiert der Raum UND ist er aktuell belegt?
        if (raum != null && raum.getStatus() == RaumStatus.BELEGT) {
            // 3. Status ändern (möglich, da wir im selben Package sind)
            raum.setStatus(RaumStatus.FREI);
            return true;
        }

        // Raum nicht gefunden oder war nicht belegt (z.B. schon frei oder gesperrt)
        return false;
    }
}
