package ch.hslu.oop.sw08.aufgabe3;

import ch.hslu.oop.sw08.aufgabe1.Temperatur;

/**
 * Beschreibt ein physikalisches Element in seinen Basiseigenschaften
 *
 * @author Tim Bernhard
 * @version 1
 */
public class Element {
    private final String elementName;
    private final float siedepunkt;
    private final float schmelzpunkt;

    public Element(String elementName, float siedepunkt, float schmelzpunkt){
        this.elementName = elementName;
        this.siedepunkt = siedepunkt;
        this.schmelzpunkt = schmelzpunkt;
    }

    public String getElementName() {
        return this.elementName;
    }

    public Aggregatzustaende getAggregatZustand(Temperatur temperatur){
        float celsius = temperatur.getCelsius();

        if (celsius < schmelzpunkt) {
            return Aggregatzustaende.SOLID;
        }
        else if (celsius < siedepunkt) {
            return Aggregatzustaende.LIQUID;
        }
        else {
            return Aggregatzustaende.GAS;
        }
    }
}
