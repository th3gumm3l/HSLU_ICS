
/**
 * Write a description of class Car here.
 *
 * @author Tim Bernhard
 * @version V1
 */
public class Car
{
    private Engine engine;
    private Light light;
    
    public Car (){
        this.engine = new GasolineEngine();
        this.light = new HalogenLamp();
        turnOnCar();
    }
    
    private void turnOnCar(){
        this.engine.switchOn();
        this.light.switchOn();
    }
    
    private void turnOffCar(){
        engine.switchOff();
        light.switchOff();
    }
}