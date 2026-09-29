package ch.hslu.oop.sw08.aufgabe2;

import ch.hslu.oop.sw08.aufgabe1.Temperatur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Klasse um die Temperatur-Verläufe von Temperatur Objekten zu "speichern"
 * @author Tim Bernhard
 * @version 1
 */
public class TemperaturVerlauf {
    private final List<Temperatur> temperaturen;

    public TemperaturVerlauf() {
        this.temperaturen = new ArrayList<Temperatur>();
    }

    public void add(Temperatur temperatur) {
        this.temperaturen.add(temperatur);
    }

    public void clear() {
        this.temperaturen.clear();
    }

    public int getCount() {
        if(this.temperaturen.isEmpty()) return 0;
        return this.temperaturen.size();
    }

    public Temperatur getMaxTemperatur(){
        if(this.temperaturen.isEmpty()) return null;
        return Collections.max(this.temperaturen);
    }

    public Temperatur getMinTemperatur(){
        if(this.temperaturen.isEmpty()) return null;
        return Collections.min(this.temperaturen);
    }

    public float getAvgTemperatur(){
        if (this.temperaturen.isEmpty()) {
            return Float.NaN;
        }

        float sum = 0f;
        for (Temperatur t : this.temperaturen) {
            sum += t.getKelvin();
        }

        return sum / this.temperaturen.size();
    }

    /**
     * Java Shenanigans
     */
    @Override
    public String toString() {
        return "TemperaturVerlauf{" +
                "temperaturen=" + temperaturen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemperaturVerlauf that)) return false;
        return Objects.equals(this.temperaturen, that.temperaturen);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(temperaturen);
    }
}
