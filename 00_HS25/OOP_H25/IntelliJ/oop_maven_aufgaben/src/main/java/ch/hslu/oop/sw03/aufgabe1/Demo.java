package ch.hslu.oop.sw03.aufgabe1;

public class Demo
{
    public int max(int a, int b){
        if (a > b){
            return a;
        }
        return b;
    }
    
    public int max(int a, int b, int c) {
        int result = a;
        if (result < b){
            result = b; 
        }
        if (result < c){
            result = c;
        }
        return result;
    }
}