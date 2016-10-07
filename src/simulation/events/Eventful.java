/***
 * NAME:    Eventful
 * PURPOSE: Inteface to define doEvent for the event object behaviour.
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;
import simulation.controller.exceptions.InvalidEventException;
import simulation.controller.Simulation;


public interface Eventful
{
    /**
     * Does the event behaviour, whatever that may be. Takes a simulation object
     * as it requires information stored there.
     */
    public void doEvent(Simulation sim) throws InvalidEventException;
}