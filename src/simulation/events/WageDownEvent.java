/***
 * NAME:    WageDownEvent
 * PURPOSE: Reduces the wages of a business unit by 5%.
 *          This is a EventBehaviour which belongs to an event.
 * AUTHOR:  Luke Healy
 * DATE:    2/10
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