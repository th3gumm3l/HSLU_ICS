
/**
 * Diese Klasse beschreibt eine Lichtmaschine mit seinen Grund-Funktionen
 *
 * @author Tim Bernhard
 * @version V1
 */
public abstract class Light implements Switchable
{
    private int lumen = 0;
    private boolean switchedOn = false;
    
    public final void switchOn(){
        this.switchedOn = true;
        lumen = 8000;
    }
    
    public final void switchOff(){
        this.switchedOn = false;
        lumen = 0;
    }
    
    public final boolean isSwitchedOn(){
        if (lumen > 0)
        {
            return true;
        }
        return false;
    }
    
    public final boolean isSwitchedOff(){
        if (lumen == 0)
        {
            return true;
        }
        return false;
    }
}
