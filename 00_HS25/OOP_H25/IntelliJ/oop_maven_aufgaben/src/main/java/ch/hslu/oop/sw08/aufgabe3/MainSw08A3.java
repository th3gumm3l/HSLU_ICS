package ch.hslu.oop.sw08.aufgabe3;

import ch.hslu.oop.sw08.aufgabe1.Temperatur;

public class MainSw08A3 {
    public static void main(String[] args) {
        Element blei = new Element("Blei", 1749f, 327.5f);
        Temperatur temperatur = new Temperatur(1000f);

        System.out.println(blei.getElementName() + " ist bei " + temperatur.getCelsius() + "°C "
                + blei.getAggregatZustand(temperatur).getBezeichnung() + ".");

    }
}
