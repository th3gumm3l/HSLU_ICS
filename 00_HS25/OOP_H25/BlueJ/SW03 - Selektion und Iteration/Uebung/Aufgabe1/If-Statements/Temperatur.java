public class Temperatur
{
    private float tempInCelsius;
    private Element element;
    
    public Temperatur()
    {
        this.tempInCelsius = 20;
    }

    public Temperatur(float tempInput)
    {
        setTempInCelsius(tempInput);
    }
    
    public Temperatur(float tempInput, Element element) {
        setTempInCelsius(tempInput);
        this.element = element;
    }

    /**
     * Get-Setter
     */
    public float getTempInCelsius(){
        return tempInCelsius;
    }
    
    public void setTempInCelsius(float tempInput){
        validateRange(tempInput);
        tempInCelsius = tempInput;
    }

    /**
     * Temp Calculations
     */
    public float getTempInKelvin(){
        return tempInCelsius + 273.15f;
    }

    public float getTempInFahrenheit(){
        return ((tempInCelsius * 1.8f)+ 32f);
    }

    public void addCelsius(float delta) {
        setTempInCelsius(this.tempInCelsius + delta);
    }

    private void validateRange(float celsius){
        if (celsius < -273.15 || celsius > 726.85){
            System.out.println("Out of Range");
        }
    }
    
    public String getAggregatZustand(){
        if (tempInCelsius < element.schmelzpunkt) {
            return "fest";
        } else if (tempInCelsius < element.siedepunkt) {
            return "flüssig";
        } else {
            return "gasförmig";
        }
    }
    
}