public class RevenueUpEvent implements Eventful
{
    private BusinessUnit property;
    private String propertyName;

    public RevenueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public void doEvent()
    {
        property = Simulation.getSimulationInstance().resolveBusinessUnit(propertyName);

    }
}