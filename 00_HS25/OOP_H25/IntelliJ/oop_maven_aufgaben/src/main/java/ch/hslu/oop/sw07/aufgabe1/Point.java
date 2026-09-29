package ch.hslu.oop.sw07.aufgabe1;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Point{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xCoordinate == point.xCoordinate && yCoordinate == point.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}