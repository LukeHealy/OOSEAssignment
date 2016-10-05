public class WageUpEvent extends WageEvent
{
    public WageUpEvent()
    {
        super();
    }

    @Override
    public void doEvent(Simulation sim)
    {
        sim.setWageChange(1.05);
    }
}