package ch.hslu.oop.sw06.aufgabe3;

/**
 * Dieses Interface implementiert einen Taschenrechner,
 * welche die Grundoperationen abdeckt.
 *
 * @author timbe
 * @version 1
 */
public interface Calculator {
    /**
     *
     * @param summand1 der erste Summand
     * @param summand2 der zweite Summand
     * @return die Summe von {@code summand1} und {@code summand2}
     */
    int addition(int summand1, int summand2);
}
