package ch.hslu.oop.sw03.aufgabe1;

public class Element
{
    String elementName;
    float siedepunkt;
    float schmelzpunkt;
    
    public Element(String elementName, float siedepunkt, float schmelzpunkt){
        this.elementName = elementName;
        this.siedepunkt = siedepunkt;
        this.schmelzpunkt = schmelzpunkt;
    }
}