public class RevenueUpEvent implements Eventful
{
    private Property property;
    private String propertyName;

    public RevenueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public void doEvent() throws InvalidEventException
    {
        BusinessUnit b;
        property = Simulation.getSimulationInstance().resolveProperty(propertyName);
        
        if((b = property.isBusinessUnit()) != null)
        {
            b.setRevenue(b.getRevenue() * 1.05);
        }
        else
        {
            throw new InvalidEventException(
                "You can only increase the revenue of a Business Unit.");
        }
    }
}