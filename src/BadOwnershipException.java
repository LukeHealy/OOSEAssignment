/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public class BadOwnershipException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public BadOwnershipException(String msg, Throwable cause)
    {
        super(("Impossible ownership: " + msg), cause);
    }

    public BadOwnershipException(String msg)
    {
        super("Impossible ownership: " + msg);
    }
}