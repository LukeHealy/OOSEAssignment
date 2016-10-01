public class WageDownEvent extends WageEvent
{
    public WageDownEvent()
    {
        super();
    }

    @Override
    public void doEvent()
    {
        sim.setWage(sim.getState() * 0.95);
    }
}