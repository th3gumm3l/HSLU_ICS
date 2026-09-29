
/**
 * Dieses Interface implementiert 4 Methoden um den Stati eines Knopfes
 * oder Switches zu beschreiben.
 *
 * @author Tim Bernhard
 * @version V1
 */
public interface Switchable
{
    void switchOn();
    void switchOff();
    boolean isSwitchedOn();
    boolean isSwitchedOff();
}