public class WageUpEvent extends WageEvent
{
    public WageUpEvent()
    {
        super();
    }

    @Override
    public void doEvent()
    {
        sim.setWage(sim.getState() * 1.05);
    }
}