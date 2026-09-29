package ch.hslu.oop.sw04.aufgabe1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPoint {

    @Test
    public void testQuadrant1() {
        Point p = new Point(5, 5);
        assertEquals(1, p.getQuadrant(), "Punkt (5,5) sollte Quadrant 1 sein");
    }

    @Test
    public void testQuadrant2() {
        Point p = new Point(-5, 5);
        assertEquals(2, p.getQuadrant(), "Punkt (-5,5) sollte Quadrant 2 sein");
    }

    @Test
    public void testQuadrant3() {
        Point p = new Point(-5, -5);
        assertEquals(3, p.getQuadrant(), "Punkt (-5,-5) sollte Quadrant 3 sein");
    }

    @Test
    public void testQuadrant4() {
        Point p = new Point(5, -5);
        assertEquals(4, p.getQuadrant(), "Punkt (5,-5) sollte Quadrant 4 sein");
    }

    @Test
    public void testAxis() {
        Point p1 = new Point(0, 5);
        Point p2 = new Point(5, 0);
        Point p3 = new Point(0, 0);
        assertEquals(0, p1.getQuadrant(), "Punkt (0,5) liegt auf der Achse");
        assertEquals(0, p2.getQuadrant(), "Punkt (5,0) liegt auf der Achse");
        assertEquals(0, p3.getQuadrant(), "Punkt (0,0) liegt auf der Achse");
    }
}