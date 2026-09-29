package ch.hslu.oop.sw06.aufgabe1.element;

/**
 * Write a description of class Shape here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Element {
    // instance variables - replace the example below with your own
    private State state; // State of matter
    private int boilingTemp;
    private int freezingTemp;

    /**
     * Constructor for objects of class Shape
     */
    protected Element(final int boilingTemp, final int freezingTemp) {
        // initialise instance variables
        this.boilingTemp = boilingTemp;
        this.freezingTemp = freezingTemp;
    }

    public State getState(int temp) {
        if (temp >= this.boilingTemp)
        {
            this.state = State.GAS;
            return this.state;
        } else if (temp >= this.freezingTemp)
        {
            this.state = State.LIQUID;
            return this.state;
        } else
        {
            this.state = State.SOLID;
            return this.state;
        }
    }

    @Override
    public String toString() {
        return "Element{" + "state=" + state + ", boilingTemp=" + boilingTemp + ", freezingTemp=" + freezingTemp + '}';
    }
}