package ch.hslu.oop.sw10.aufgabe2;

import ch.hslu.oop.sw09.aufgabe2.Temperatur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TemperaturVerlauf verlauf = new TemperaturVerlauf();

        // Typische Listener-Implementierung über anonyme Klasse/Lambda
        verlauf.addListener(event -> {
            if (event.getTyp() == TemperaturEvent.Typ.MAXIMUM) {
                System.out.println("Neues Maximum erreicht: " + event.getTemperatur());
            }
            if (event.getTyp() == TemperaturEvent.Typ.MINIMUM) {
                System.out.println("Neues Minimum erreicht: " + event.getTemperatur());
            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.println("Temperatur-Eingabe. Geben Sie 'exit' zum Beenden ein.");

        while (true) {
            System.out.print("Temperaturwert (in Celsius): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
            try {
                float celsius = Float.parseFloat(input);
                Temperatur t = Temperatur.createFromCelsius(celsius);
                verlauf.add(t);
            } catch (Exception e) {
                System.out.println("Ungültige Eingabe: " + e.getMessage());
            }
        }

        System.out.println("\nStatistik:");
        System.out.println("Anzahl Werte: " + verlauf.getCount());
        System.out.println("Durchschnittstemperatur (Kelvin): " + verlauf.getAvgTemperatur());
        System.out.println("Minimum: " + verlauf.getMinTemperatur());
        System.out.println("Maximum: " + verlauf.getMaxTemperatur());
    }
}