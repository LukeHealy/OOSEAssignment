public class WageDownEvent extends WageEvent
{
    public WageDownEvent()
    {
        super();
    }

    @Override
    public void doEvent(Simulation sim)
    {
        sim.setWageChange(0.95);
    }
}