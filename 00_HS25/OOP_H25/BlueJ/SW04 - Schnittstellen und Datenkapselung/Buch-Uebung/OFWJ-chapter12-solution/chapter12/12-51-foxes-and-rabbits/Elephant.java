import java.util.Iterator;
import java.util.List;

/**
 * A simple model of an elephant.
 * Elephants move and trample other animals.
 * They do not breed within the timescale of the simulation,
 * and they cannot die.
 * 
 * @author David J. Barnes and Michael Kölling and Poul Henricksen
 * @version 2016.02.29
 */
public class Elephant extends Animal
{
    // Characteristics shared by all elephants (class variables).
    
    // The age at which an elephant can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which an elephant can live.
    private static final int MAX_AGE = 10000;
    // The likelihood of an elephant breeding.
    private static final double BREEDING_PROBABILITY = 0;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 0;

    /**
     * Create an elephant. An elephant can be created as a new born (age zero)
     * or with a random age.
     * 
     * @param randomAge If true, the elephant will have random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Elephant(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location);
    }

    /**
     * This is what the elephant does most of the time: it wanders
     * the field trampling on other animals.
     * @param field The field currently occupied.
     * @param newElephants A list to add newly born elephants to.
     */
    public void act(List<Animal> newElephants)
    {
        if(isAlive()) {
            Location newLocation = trample();
            // See if we trampled anything.
            if(newLocation == null) { 
                // Nothing trampled - look elsewhere.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Will have to wait patiently.
            }
        }
    }

    /**
     * Return the maximum age for an elephant.
     * @return The elephant's maximum age.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }
    
    /**
     * Return the breeding age for an elephant.
     * @return The elephant's breeding age.
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * Return the breeding probability for an elephant.
     * @return The elephant's probability age.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * Return the maximum litter size for an elephant.
     * @return The elephant's maximum litter size.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * Elephants are death resistent.
     */
    public void setDead()
    {
    }

    /**
     * Tell the elephant to trample non-elephants adjacent to its
     * current location.
     * @return One of the locations it has trampled into.
     */
    private Location trample()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        Location trampled = null;
        while(it.hasNext()) {
            Location where = it.next();
            Object obj = field.getObjectAt(where);
            // Don't trample other elephants.
            if(obj instanceof Animal && ! (obj instanceof Elephant)) {
                Animal animal = (Animal) obj;
                if(animal.isAlive()) { 
                    animal.setDead();
                    trampled = where;
                }
            }
        }
        return trampled;
    }

}
