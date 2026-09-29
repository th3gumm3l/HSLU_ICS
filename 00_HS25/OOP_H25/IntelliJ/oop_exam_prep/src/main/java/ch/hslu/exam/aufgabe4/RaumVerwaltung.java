package ch.hslu.exam.aufgabe4;

import ch.hslu.exam.aufgabe2.RaumStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaumVerwaltung {
    private List<Raum> raumListe;

    public RaumVerwaltung() {
        raumListe = new ArrayList<>();
        raumListe.add(new Raum(602, 6));
        raumListe.add(new Raum(603, 12));
        raumListe.add(new Raum(600, 18));
        raumListe.add(new Raum(605, 24));
        raumListe.add(new Raum(610, 12));
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

    private void addRaum(Raum raum) {
        if (getRaumWithNumber(raum.getRaumNummer()) != null) {
            throw new IllegalStateException("Raum ist bereits verwaltet");
        }
        this.raumListe.add(raum);

        Collections.sort(this.raumListe);
    }

    private int getRaumNumber(int number) {
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
        for (Raum raum : this.raumListe) {
            System.out.println(raum);
        }
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

    @Override
    public String toString() {
        return "RaumVerwaltung{" +
                "raumListe=" + raumListe +
                '}';
    }
}
