package ch.hslu.oop.sw06.aufgabe1;

public class Point
{
    private int xCoordinate;
    private int yCoordinate;
    
    public Point(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Point(Point point){
        this.xCoordinate = point.xCoordinate;
        this.yCoordinate = point.yCoordinate;
    }

    public int getQuadrant(){
        if (xCoordinate > 0 && yCoordinate > 0){
            return 1;
        }
        if (xCoordinate < 0 && yCoordinate > 0){
            return 2;
        }
        if (xCoordinate < 0 && yCoordinate < 0){
            return 3;
        }
        if (xCoordinate > 0 && yCoordinate < 0){
            return 4;
        }
        else {
            return 0;
        }
    }

    public void moveRelative(int x, int y){
        this.xCoordinate += x;
        this.yCoordinate += y;
    }

    public void moveRelative(Point point){
        this.xCoordinate += point.xCoordinate;
        this.yCoordinate += point.yCoordinate;
    }

    public void moveRelative(double betrag, double winkel){
        this.xCoordinate = (int) (betrag * Math.cos(winkel));
        this.yCoordinate = (int) (betrag * Math.sin(winkel));
    }



}