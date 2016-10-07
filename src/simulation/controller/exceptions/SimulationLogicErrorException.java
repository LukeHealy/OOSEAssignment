/***
 * NAME:    SimulationLogicError
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.exceptions;

public class SimulationLogicError extends Exception
{
    /**
     * Standard exception constructor.
     */
    public SimulationLogicError(String msg, Throwable cause)
    {
        super(("Error in simulation logic: " + msg), cause);
    }

    public SimulationLogicError(String msg)
    {
        super("Error in simulation logic: " + msg);
    }
}