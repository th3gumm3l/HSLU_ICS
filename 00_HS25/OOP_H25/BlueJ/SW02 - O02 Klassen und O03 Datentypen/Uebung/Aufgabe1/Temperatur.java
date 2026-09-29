public class Temperatur
{
    private float tempInCelsius;

    public Temperatur()
    {
        this.tempInCelsius = 20;
    }

    public Temperatur(float tempInput)
    {
        SetTempInCelsius(tempInput);
    }

    /**
     * Get-Setter
     */
    public float GetTempInCelsius(){
        return tempInCelsius;
    }
    
    public void SetTempInCelsius(float tempInput){
        ValidateRange(tempInput);
        tempInCelsius = tempInput;
    }

    /**
     * Temp Calculations
     */
    public float GetTempInKelvin(){
        return tempInCelsius + 273.15f;
    }

    public float GetTempInFahrenheit(){
        return ((tempInCelsius * 1.8f)+ 32f);
    }

    public void addCelsius(float delta) {
        SetTempInCelsius(this.tempInCelsius + delta);
    }

    private void ValidateRange(float celsius){
        if (celsius < -273.15 || celsius > 726.85){
            System.out.println("Out of Range");
        }
    }
}