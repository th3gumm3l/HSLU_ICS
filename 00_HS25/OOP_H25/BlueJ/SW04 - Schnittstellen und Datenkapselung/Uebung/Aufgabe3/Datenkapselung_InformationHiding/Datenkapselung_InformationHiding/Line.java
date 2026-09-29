/**
 * tbd
 *
 * @author Tim Bernhard
 * @version 1
 */
public class Line
{
    private Point startPunkt;
    private Point endPunkt;
    
    public Line(int x1, int y1,
        int x2, int y2)
    {
        setCoordinateOfStartPoint(x1, y1);
        setCoordinateOfEndPoint(x2, y2);
    }
    
    public Line(Point start, Point end){
        this.startPunkt = start;
        this.endPunkt = end;
    }
    
    /**
     * Getter Methode für StartPunkt
     */
    public Point getCoordinateOfStartPoint(){
        return startPunkt;
    }
    
    /**
     * Getter Methode für EndPunkt
     */
    public Point getCoordinateOfEndPoint(){
        return endPunkt;
    }
    
    /**
     * Setter Methode für StartPunkt
     */
    public void setCoordinateOfStartPoint(int xInput, int yInput){
        this.startPunkt = new Point(xInput, yInput);
    }
    
    /**
     * Setter Methode für EndPunkt
     */
    public void setCoordinateOfEndPoint(int xInput, int yInput){
        this.endPunkt = new Point(xInput, yInput);
    }
}