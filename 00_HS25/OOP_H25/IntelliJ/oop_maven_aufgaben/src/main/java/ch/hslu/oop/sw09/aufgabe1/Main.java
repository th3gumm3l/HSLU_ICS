package ch.hslu.oop.sw09.aufgabe1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Bitte Temperatur eingeben (oder 'exit' zum Beenden): ");
            input = scanner.next();

            logger.debug("Eingabe erhalten: {}", input);

            if ("exit".equalsIgnoreCase(input)) {
                logger.info("Exit-Befehl erhalten, Beenden wird gestartet");
                break;
            }

            if (isNumeric(input)) {
                System.out.println("Eingegebene Temperatur: " + input);
                logger.info("Gültige Temperatur eingegeben: {}", input);
            } else {
                System.out.println("Keine gültige Nummer");
                logger.warn("Ungültige Eingabe: {}", input);
            }
        } while (true);

        System.out.println("Programm beendet.");
        logger.info("Programm beendet.");
    }

    public static boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            logger.error("Fehler beim Parsen der Eingabe '{}' als Zahl", str, e);
            return false;
        }
    }
}
