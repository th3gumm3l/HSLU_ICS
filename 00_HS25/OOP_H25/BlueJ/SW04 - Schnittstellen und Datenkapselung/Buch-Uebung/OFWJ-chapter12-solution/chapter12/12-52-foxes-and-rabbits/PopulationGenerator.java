import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * A class to generate the foxes and rabbits populations of the simulation.
 * 
 * @author David J. Barnes and Michael Kölling and Poul Henricksen
 * @version 2016.02.29
 */
public class PopulationGenerator
{
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;    

    /**
     * Constructor for objects of class PopulationGenerator
     * @param view The visualization.
     */
    public PopulationGenerator(SimulatorView view)
    {
        // Setup associations between the animals and colors
        // for the visualization.
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     * @param field The field to be populated.
     * @param animals A list of all the animals generated.
     */
    public void populate(Field field, List<Animal>animals)
    {
        Random rand = Randomizer.getRandom();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    }
}
