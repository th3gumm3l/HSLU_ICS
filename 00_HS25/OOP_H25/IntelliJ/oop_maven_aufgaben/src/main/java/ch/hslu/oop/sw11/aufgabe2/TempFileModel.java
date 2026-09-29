package ch.hslu.oop.sw11.aufgabe2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempFileModel {
    public List<String> weNotCare = new ArrayList<>();
    public List<LocalDateTime> timestamp = new ArrayList<>();
    public List<Float> temperatur = new ArrayList<>();
    public List<String> luftfeuchtigkeit = new ArrayList<>();

    public float maxTemperatur() {
        if (temperatur.isEmpty()) {
            throw new IllegalStateException("Temperatur-Liste ist leer");
        }
        return Collections.max(this.temperatur);
    }

    public float minTemperatur() {
        if (temperatur.isEmpty()) {
            throw new IllegalStateException("Temperatur-Liste ist leer");
        }
        return Collections.min(this.temperatur);
    }

    public float avgTemperatur() {
        if (temperatur.isEmpty()) {
            throw new IllegalStateException("Temperatur-Liste ist leer");
        }
        float sum = 0;
        for (Float f : this.temperatur) {
            sum += f;
        }
        return sum / this.temperatur.size();
    }

    public String maxTemperaturAndTime() {
        int maxIndex = this.temperatur.indexOf(maxTemperatur());
        LocalDateTime maxTime = this.timestamp.get(maxIndex);

        return "Timestamp: " + maxTime.toString() + " Temperatur: " + maxTemperatur();
    }

    public String minTemperaturAndTime() {
        int minIndex = this.temperatur.indexOf(this.minTemperatur());
        LocalDateTime minTime = this.timestamp.get(minIndex);

        return "Timestamp: " + minTime.toString() + " Temperatur: " + minTemperatur();
    }

}