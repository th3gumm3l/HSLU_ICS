package ch.hslu.oop.sw08.aufgabe1;

import java.util.Objects;

/**
 * Beschreibt die Temperatur in ihren Basiseigenschaften und Methoden
 *
 * @author Tim Bernhard
 * @version 1
 */
public final class Temperatur implements Comparable<Temperatur> {
    private float kelvin;
    private final static float KELVIN_OFFSET = 273.15f;

    public Temperatur(float kelvin) {
        setKelvin(kelvin);
    }

    public Temperatur(Temperatur temperatur) {
        this.kelvin = temperatur.kelvin;
    }

    public float getKelvin() {
        return kelvin;
    }

    public void setKelvin(float kelvin) {
        this.kelvin = kelvin;
    }

    public float getCelsius() {
        return convertKelvinToCelsius(kelvin);
    }

    public void setCelsius(float celsius) {
        this.kelvin = convertCelsiusToKelvin(celsius);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Temperatur that)) return false;
        return Float.compare(getKelvin(), that.getKelvin()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKelvin());
    }

    @Override
    public String toString() {
        return "Temperatur{" + "kelvin=" + kelvin + '}';
    }

    @Override
    public int compareTo(Temperatur temperatur) {
        return Float.compare(this.kelvin, temperatur.kelvin);
    }

    /*
    Hilfsfunktionen
     */
    public static float convertKelvinToCelsius(float kelvin) {
        return roundResult(kelvin - KELVIN_OFFSET);
    }

    public static float convertCelsiusToKelvin(float celsius) {
        return roundResult(celsius + KELVIN_OFFSET);
    }

    private static float roundResult(float input) {
        return Math.round(input * 100.0f) / 100.0f;
    }
}
