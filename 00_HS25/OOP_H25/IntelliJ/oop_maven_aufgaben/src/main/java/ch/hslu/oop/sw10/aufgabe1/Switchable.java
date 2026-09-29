package ch.hslu.oop.sw10.aufgabe1;

/**
 * Dieses Interface implementiert 4 Methoden um den Stati eines Knopfes
 * oder Schalters zu beschreiben.
 *
 * @author Tim Bernhard
 * @version V1
 */
public interface Switchable {
    void switchOn();
    void switchOff();
    boolean isSwitchedOn();
    boolean isSwitchedOff();
}
