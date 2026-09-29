package ch.hslu.oop.sw05.aufgabe1;

/**
 * Diese Klasse abstrahiert das Gebilde einer Form. Daraus soll
 * dann Objekte wie Kreise, Rechtecke, Vierecke usw. erstellt werden
 * können.
 *
 * @author Tim Bernhard
 * @version 1
 */
public abstract class Shape
{
    private int x;
    private int y;
    
    protected Shape (final int x, final int y){
        this.x = x;
        this.y = y;
    }
    
    public final void move(final int newX, final int newY){
        this.x = newX;
        this.y = newY;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public abstract int getPerimeter();
    
    public abstract int getArea();
}