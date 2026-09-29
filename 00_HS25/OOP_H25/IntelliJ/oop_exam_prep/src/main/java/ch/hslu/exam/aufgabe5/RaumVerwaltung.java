package ch.hslu.exam.aufgabe5;

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class RaumVerwaltung {

    private final Map<Integer, Raum> raeume;
    private final List<RaumListener> listeners;

    public RaumVerwaltung() {
        this.raeume = new TreeMap<>();
        this.listeners = new ArrayList<>();
        // Testdaten (SW12)
        addRaum(new Raum(600, 10));
        addRaum(new Raum(602, 20));
        addRaum(new Raum(605, 30));
    }

    /**
     * Fügt einen Raum hinzu.
     * Nur innerhalb des Packages sichtbar (für Tests und später Konstruktor-Initialisierung).
     */
    void addRaum(Raum raum) {
        raeume.put(raum.getRaumnummer(), raum);
    }

    public void addRaumListener(RaumListener listener) {
        listeners.add(listener);
    }

    private void fireRaumEvent(Raum raum, int anzahlPersonen, String type) {
        RaumEvent event = new RaumEvent(this, raum, anzahlPersonen, type);
        for (RaumListener listener : listeners) {
            listener.onRaumEvent(event);
        }
    }

    public Raum getRaum(int raumnummer) {
        return raeume.get(raumnummer);
    }

    /**
     * Gibt alle Räume zurück (unveränderlich).
     */
    public Collection<Raum> getAllRaeume() {
        return Collections.unmodifiableCollection(raeume.values());
    }

    /**
     * Reserviert den besten passenden freien Raum (Best-Fit).
     * @param anzahlPersonen Benötigte Plätze
     * @return Reservierter Raum oder empty, falls keiner gefunden.
     */
    public Optional<Raum> reservieren(int anzahlPersonen) {
        Raum bestMatch = null;

        for (Raum r : raeume.values()) {
            if (r.isFrei() && r.getKapazitaet() >= anzahlPersonen) {
                if (bestMatch == null || r.getKapazitaet() < bestMatch.getKapazitaet()) {
                    bestMatch = r;
                }
            }
        }

        if (bestMatch != null) {
            bestMatch.setStatus(RaumStatus.BELEGT);
            fireRaumEvent(bestMatch, anzahlPersonen, "RESERVIERT");
            return Optional.of(bestMatch);
        }

        return Optional.empty();
    }

    /**
     * Gibt einen Raum wieder frei.
     * @param raumnummer Nummer des Raums
     */
    public void freigeben(int raumnummer) {
        Raum r = raeume.get(raumnummer);
        if (r != null) {
            r.setStatus(RaumStatus.FREI);
            fireRaumEvent(r, r.getKapazitaet(), "FREIGEGEBEN");
        }
    }
}
