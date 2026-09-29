import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    
    // Individual characteristics (instance fields).
    

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location);
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newRabbits)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Return the maximum age for a rabbit.
     * @return The animal's maximum age.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }
        
    /**
     * Return the breeding age for a rabbit.
     * @return The rabbit's breeding age.
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * Return the breeding probability for a rabbit.
     * @return The rabbit's probability age.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * Return the maximum litter size for a rabbit.
     * @return The rabbit's maximum litter size.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }
        
    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    protected Animal createAnimal(boolean randomAge, Field field, Location location)
    {
        return new Rabbit(randomAge, field, location);
    }
            
}
