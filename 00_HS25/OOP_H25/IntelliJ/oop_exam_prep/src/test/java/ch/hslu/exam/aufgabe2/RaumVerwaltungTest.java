package ch.hslu.exam.aufgabe2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaumVerwaltungTest {

    @Test
    void getRaumNumber() {
        // Arrange
        RaumVerwaltung verwaltung = new RaumVerwaltung();

        // Act
        // Raum 603 wird im Konstruktor der Verwaltung angelegt
        Raum ergebnis = verwaltung.getRaumWithNumber(603);

        // Assert
        assertNotNull(ergebnis, "Raum 603 sollte existieren.");
        assertEquals(603, ergebnis.getRaumNummer());
        assertEquals(12, ergebnis.getKapazitaet());
    }

    @Test
    void getRaumWithNumber() {
        // Arrange
        RaumVerwaltung verwaltung = new RaumVerwaltung();

        // Act
        // Wir suchen einen Raum, der nicht existiert (z.B. 100)
        Raum ergebnis = verwaltung.getRaumWithNumber(100);

        // Assert
        // Im Fehlerfall (nicht gefunden) erwarten wir null
        assertNull(ergebnis, "Für nicht existierende Raumnummern soll null zurückkommen.");
    }
}