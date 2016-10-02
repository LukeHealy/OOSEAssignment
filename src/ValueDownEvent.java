public class ValueDownEvent implements Eventful
{
    private Property property;
    private String propertyName;

    public ValueDownEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public void doEvent() throws InvalidEventException
    {
        property = Simulation.getSimulationInstance().resolveProperty(propertyName);
        
        property.setMonetaryValue(property.getMonetaryValue() * 0.95);
    }
}