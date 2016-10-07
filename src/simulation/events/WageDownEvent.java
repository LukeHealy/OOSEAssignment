/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;

import simulation.controller.Simulation;

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