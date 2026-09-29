package ch.hslu.oop.sw03.aufgabe1;

public class Point
{
    private int xCoordinate;
    private int yCoordinate;
    
    public Point(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
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
}