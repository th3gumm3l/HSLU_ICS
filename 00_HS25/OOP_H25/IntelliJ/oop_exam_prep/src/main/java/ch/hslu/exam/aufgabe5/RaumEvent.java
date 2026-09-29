package ch.hslu.exam.aufgabe5;

import java.util.EventObject;

public class RaumEvent extends EventObject {

    private final Raum raum;
    private final int anzahlPersonen;
    private final String type; // "RESERVIERT" or "FREIGEGEBEN"

    public RaumEvent(Object source, Raum raum, int anzahlPersonen, String type) {
        super(source);
        this.raum = raum;
        this.anzahlPersonen = anzahlPersonen;
        this.type = type;
    }

    public Raum getRaum() {
        return raum;
    }

    public int getAnzahlPersonen() {
        return anzahlPersonen;
    }

    public String getType() {
        return type;
    }
}
