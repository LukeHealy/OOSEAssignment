/***
 * NAME:    
 * PURPOSE: Super class of a wage event.
 *          This is a EventBehaviour which belongs to an event.
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;

import simulation.controller.Simulation;

public abstract class WageEvent implements Eventful
{
    public abstract void doEvent(Simulation sim);
}