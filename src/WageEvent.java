public abstract class WageEvent implements Eventful
{
    protected Simulation sim;

    public WageEvent()
    {
        sim = Simulation.getSimulationInstance();
    }

    public abstract void doEvent();
}