/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;

import simulation.controller.Simulation;

public abstract class WageEvent implements Eventful
{
    public abstract void doEvent(Simulation sim);
}