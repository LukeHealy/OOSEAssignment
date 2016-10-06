package simulation.events;

import simulation.controller.Simulation;

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