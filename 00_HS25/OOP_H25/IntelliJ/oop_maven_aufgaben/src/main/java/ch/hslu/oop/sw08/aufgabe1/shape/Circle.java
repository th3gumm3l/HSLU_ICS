package ch.hslu.oop.sw08.aufgabe1.shape;

/**
 * Diese Klasse spezialisiert eine Form auf einen Kreis.
 *
 * @author Tim Bernhard
 * @version 1
 */
public final class Circle extends Shape
{
    private int diameter;
    protected int pi = 3;
    
    public Circle(final int x, final int y, final int diameter){
        super(x, y);
        this.diameter = diameter;
    }
    
    public void setDiameter(int diameter){
        this.diameter = diameter;
    }
    
    public int getDiameter(){
        return this.diameter;
    }
    
    @Override
    public int getPerimeter(){
        return (this.diameter * this.pi);
    }
    
    @Override
    public int getArea(){
        return (((this.diameter / 2) * (this.diameter / 2)) * this.pi);
    }
}