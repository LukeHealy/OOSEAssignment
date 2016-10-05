public class RevenueUpEvent implements Eventful
{
    private String propertyName;

    public RevenueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

      @Override
    public void doEvent(Simulation sim) throws InvalidEventException
    {
        Property property = sim.resolveProperty(propertyName);
        BusinessUnit b;
        
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