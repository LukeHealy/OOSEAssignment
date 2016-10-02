public class ValueUpEvent implements Eventful
{
    private Property property;
    private String propertyName;

    public ValueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public void doEvent() throws InvalidEventException
    {
        property = Simulation.getSimulationInstance().resolveProperty(propertyName);
        
        property.setMonetaryValue(property.getMonetaryValue() * 1.05);
    }
}