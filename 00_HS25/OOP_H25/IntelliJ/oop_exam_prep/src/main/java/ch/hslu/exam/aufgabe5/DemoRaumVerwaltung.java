package ch.hslu.exam.aufgabe5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoRaumVerwaltung {

    private static final Logger LOG = LoggerFactory.getLogger(DemoRaumVerwaltung.class);

    public static void main(String[] args) {
        RaumVerwaltung rv = new RaumVerwaltung();

        rv.addRaumListener(e -> LOG.info("Event: Type={}, Raum={}, Plätze={}", 
                e.getType(), e.getRaum().getRaumnummer(), e.getAnzahlPersonen()));

        System.out.println("Alle Räume (initial):");
        rv.getAllRaeume().forEach(System.out::println);

        System.out.println("\nReserviere für 8 Personen...");
        rv.reservieren(8).ifPresent(r -> System.out.println("Reserviert: " + r));

        System.out.println("\nReserviere für 18 Personen...");
        rv.reservieren(18).ifPresent(r -> System.out.println("Reserviert: " + r));

        System.out.println("\nReserviere für 25 Personen...");
        rv.reservieren(25).ifPresent(r -> System.out.println("Reserviert: " + r));
        
        System.out.println("\nGebe Raum 602 frei...");
        rv.freigeben(602);

        System.out.println("\nAlle Räume (nach Reservation/Freigabe):");
        rv.getAllRaeume().forEach(System.out::println);
    }
}
