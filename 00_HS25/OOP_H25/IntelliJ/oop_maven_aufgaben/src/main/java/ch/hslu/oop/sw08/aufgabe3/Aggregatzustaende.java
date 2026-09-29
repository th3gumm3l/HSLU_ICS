package ch.hslu.oop.sw08.aufgabe3;

/**
 * Beschreibt die Aggregatszustände eines physikalischen Elements
 *
 * Eine Methode lässt noch die Bezeichnung des Aggregatszustands
 * als String auslesen
 *
 * @author Tim Bernhard
 * @version 1
 */
public enum Aggregatzustaende {
    SOLID("Fest"),
    LIQUID("Flüssig"),
    GAS("Gasförmig");

    private final String bezeichnung;

    Aggregatzustaende(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
