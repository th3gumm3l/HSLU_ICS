package ch.hslu.oop.sw07.aufgabe1;

import java.util.Objects;

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

    public String getElementName() {
        return elementName;
    }
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public float getSiedepunkt() {
        return siedepunkt;
    }

    public void setSiedepunkt(float siedepunkt) {
        this.siedepunkt = siedepunkt;
    }

    public float getSchmelzpunkt() {
        return schmelzpunkt;
    }

    public void setSchmelzpunkt(float schmelzpunkt) {
        this.schmelzpunkt = schmelzpunkt;
    }

    @Override
    public String toString() {
        return "Element{" +
                "elementName='" + elementName + '\'' +
                ", siedepunkt=" + siedepunkt +
                ", schmelzpunkt=" + schmelzpunkt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Float.compare(siedepunkt, element.siedepunkt) == 0 && Float.compare(schmelzpunkt, element.schmelzpunkt) == 0 && Objects.equals(elementName, element.elementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementName, siedepunkt, schmelzpunkt);
    }
}