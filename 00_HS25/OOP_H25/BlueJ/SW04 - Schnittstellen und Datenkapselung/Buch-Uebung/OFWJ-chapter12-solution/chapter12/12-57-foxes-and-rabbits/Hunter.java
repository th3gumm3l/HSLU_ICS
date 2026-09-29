import java.util.Iterator;
import java.util.List;

/**
 * Hunters kill animals.
 * 
 * @author David J. Barnes and Michael Kölling and Poul Henricksen
 * @version 2016.02.29
 */
public class Hunter implements Actor
{
    // The maximum number of animals to be killed at any one step.
    private static final int MAX_KILLS = 4;
    // The field the hunter is in.
    private Field field;
    // The location of the hunter.
    private Location location;
    
    /**
     * Constructor for objects of class Hunter
     */
    public Hunter(Field field, Location location)
    {
        this.field = field;
        setLocation(location);
    }

    /**
     * A hunter is always active.
     * @return true
     */
    public boolean isActive()
    {
        return true;
    }
    
    /**
     * Perform the hunter’s regular behavior.
     * Hunters kill a random number of animals in adjacent locations.
     * @param newActors A list for storing newly created actors.
     */
    public void act(List<Actor> newActors)
    {
        int kills = 0;
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();

        while(it.hasNext() && kills < MAX_KILLS) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if(actor instanceof Animal) {
                Animal animal = (Animal) actor;
                animal.setDead();
                kills++;
            }
        }
        // Try to move to a free adjacent location.
        Location newLocation = field.freeAdjacentLocation(location);
        if(newLocation != null) {
            setLocation(newLocation);
        }
    }

    /**
     * Place the hunter at the new location in the given field.
     * @param newLocation The hunter's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
}
