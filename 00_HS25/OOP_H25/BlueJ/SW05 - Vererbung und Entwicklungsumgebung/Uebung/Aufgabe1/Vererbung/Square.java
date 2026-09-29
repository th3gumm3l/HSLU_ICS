
/**
 * Diese Klasse spezialisiert die abstrakte Klasse Shape zu einem Square.
 *
 * @author Tim Bernhard
 * @version 1
 */
public class Square extends Shape
{
    private int width;
    
    public Square(final int x, final int y, final int width) {
        super(x, y);
        this.width = width;
    }
    
    public void setWidth(final int newWidth){
        this.width = newWidth;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    @Override
    public int getPerimeter(){
        return (4*this.width);
    }
    
    @Override
    public int getArea(){
        return (this.width * this.width);
    }
}