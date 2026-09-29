package ch.hslu.oop.sw06.aufgabe1.element;

/**
 * Write a description of class Blei here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Quecksilber extends Element
{
    // instance variables - replace the example below with your own
    private static final int BOILING_TEMP = 356; // celsius
    private static final int FREEZING_TEMP = -39; //celsius
    private String name;
    private String symbol;

    /**
     * Constructor for objects of class Blei
     */
    public Quecksilber()
    {
        // initialise instance variables
        super(BOILING_TEMP, FREEZING_TEMP);
        this.name = "Quecksilber";
        this.symbol = "Hg";
    }

    @Override
    public String toString() {
        return "GIFTIG" + super.toString();
    }
}