/***
 * NAME:    InvalidPlanException
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public class InvalidPlanException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public InvalidPlanException(String msg, Throwable cause)
    {
        super(("Invalid buy or sell plan: " + msg), cause);
    }

    public InvalidPlanException(String msg)
    {
        super("Invalid buy or sell plan " + msg);
    }
}