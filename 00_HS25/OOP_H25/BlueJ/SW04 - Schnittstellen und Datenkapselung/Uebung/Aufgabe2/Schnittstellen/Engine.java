
/**
 * Diese Klasse beschreibt einen Motor mit seinen Grund-Funktionen
 *
 * @author Tim Bernhard
 * @version V1
 */
public abstract class Engine implements Switchable
{
    private int rpm = 0;
    private boolean switchedOn = false;
    
    public final void switchOn(){
        this.switchedOn = true;
        rpm = 800;
    }
    
    public final void switchOff(){
        this.switchedOn = false;
        rpm = 0;
    }
    
    public final boolean isSwitchedOn(){
        if (rpm > 0)
        {
            return true;
        }
        return false;
    }
    
    public final boolean isSwitchedOff(){
        if (rpm == 0)
        {
            return true;
        }
        return false;
    }
}