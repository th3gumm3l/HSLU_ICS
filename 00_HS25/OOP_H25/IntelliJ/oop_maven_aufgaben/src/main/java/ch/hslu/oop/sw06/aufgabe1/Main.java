package ch.hslu.oop.sw06.aufgabe1;

import ch.hslu.oop.sw06.aufgabe1.shape.Circle;
import ch.hslu.oop.sw06.aufgabe1.shape.Rectangle;
import ch.hslu.oop.sw06.aufgabe1.shape.Shape;

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle(1,2,3);
        Shape shape2 = new Rectangle(1,2,3,4);

        shape1.move(2,3);
        shape2.move(4,5);

        int diameter = ((Circle) shape1).getDiameter();
    }
}
