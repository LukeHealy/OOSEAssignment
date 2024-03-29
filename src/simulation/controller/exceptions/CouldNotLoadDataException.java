/***
 * NAME:    InvalidFileException
 * PURPOSE: To be thrown from FileIO up to the Controller
 *          when the file is deemed invalid. 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.exceptions;

public class CouldNotLoadDataException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public CouldNotLoadDataException(String msg, Throwable cause)
    {
        super(("Couldn't load data: " + msg), cause);
    }
    public CouldNotLoadDataException(String msg)
    {
        super("Couldn't load data: " + msg);
    }
}