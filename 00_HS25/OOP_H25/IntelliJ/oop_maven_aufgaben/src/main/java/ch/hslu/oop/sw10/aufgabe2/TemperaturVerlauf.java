package ch.hslu.oop.sw10.aufgabe2;

import ch.hslu.oop.sw09.aufgabe2.Temperatur;

import java.util.*;

public class TemperaturVerlauf {
    private final List<Temperatur> temperaturen;
    private final List<TemperaturVerlaufListener> listeners = new ArrayList<>();
    private Temperatur max;
    private Temperatur min;

    public TemperaturVerlauf() {
        this.temperaturen = new ArrayList<>();
        max = null;
        min = null;
    }

    public void addListener(TemperaturVerlaufListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TemperaturVerlaufListener listener) {
        listeners.remove(listener);
    }

    public void add(Temperatur t) {
        if (t.getKelvin() < 0.0f) {
            throw new IllegalArgumentException("Temperaturwert darf nicht negativ sein.");
        }
        this.temperaturen.add(t);

        boolean isNewMin = min == null || t.compareTo(min) < 0;
        boolean isNewMax = max == null || t.compareTo(max) > 0;

        if (isNewMin) {
            min = t;
            fireEvent(new TemperaturEvent(this, min, TemperaturEvent.Typ.MINIMUM));
        }
        if (isNewMax) {
            max = t;
            fireEvent(new TemperaturEvent(this, max, TemperaturEvent.Typ.MAXIMUM));
        }
    }

    private void fireEvent(TemperaturEvent event) {
        for (TemperaturVerlaufListener listener : listeners) {
            listener.handleTemperaturEvent(event);
        }
    }

    public void clear() {
        temperaturen.clear();
        min = null;
        max = null;
    }

    public int getCount() {
        return temperaturen.size();
    }

    public Temperatur getMaxTemperatur() { return max; }

    public Temperatur getMinTemperatur() {
        return min;
    }

    public float getAvgTemperatur() {
        if (temperaturen.isEmpty()){
            return Float.NaN;
        }
        float sum = 0f;
        for (Temperatur t : temperaturen) sum += t.getKelvin();
        return sum / temperaturen.size();
    }

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