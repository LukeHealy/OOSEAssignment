/***
 * NAME:    
 * PURPOSE: Increases the wages of a business unit by 5%.
 *          This is a EventBehaviour which belongs to an event.
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;

import simulation.controller.Simulation;

public class WageUpEvent implements Eventful
{
    public void doEvent(Simulation sim)
    {
        sim.setWageChange(1.05);
    }
}