/***
 * NAME:    InvalidPlanException
 * PURPOSE: To be thrown from FileIO up to the Controller
 *          when the file is deemed invalid. 
 * AUTHOR:  Luke Healy
 * DATE:    2/10/16
 */

public class InvalidEventException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public InvalidEventException(String msg, Throwable cause)
    {
        super(("Invalid Plan: " + msg), cause);
    }

    public InvalidEventException(String msg)
    {
        super("Invalid Plan: " + msg);
    }
}