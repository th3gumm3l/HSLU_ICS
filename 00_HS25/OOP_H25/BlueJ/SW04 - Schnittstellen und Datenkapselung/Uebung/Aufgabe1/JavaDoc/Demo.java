/**
 * Die Klasse {@code Demo} enthält Methoden zur Bestimmung des Maximums
 * aus zwei oder drei Ganzzahlen.
 * <p>
 * Sie demonstriert Methodenüberladung (Overloading) anhand der Methode {@code max}.
 * </p>
 * 
 * @author Tim Bernhard
 * @version 1.0
 */
public class Demo
{
    /**
     * Gibt die größere von zwei ganzen Zahlen zurück.
     *
     * @param a die erste Zahl
     * @param b die zweite Zahl
     * @return die größere der beiden Zahlen {@code a} oder {@code b}
     */
    public int max(int a, int b){
        if (a > b){
            return a;
        }
        return b;
    }
    
    /**
     * Gibt die größte von drei ganzen Zahlen zurück.
     *
     * @param a die erste Zahl
     * @param b die zweite Zahl
     * @param c die dritte Zahl
     * @return die größte der drei Zahlen {@code a}, {@code b} oder {@code c}
     */
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