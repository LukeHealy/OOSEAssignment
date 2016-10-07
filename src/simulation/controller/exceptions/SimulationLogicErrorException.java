/***
 * NAME:    SimulationLogicError
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.exceptions;

public class SimulationLogicErrorException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public SimulationLogicErrorException(String msg, Throwable cause)
    {
        super(("Error in simulation logic: " + msg), cause);
    }

    public SimulationLogicErrorException(String msg)
    {
        super("Error in simulation logic: " + msg);
    }
}