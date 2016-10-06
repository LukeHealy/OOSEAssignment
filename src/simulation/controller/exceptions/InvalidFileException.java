/***
 * NAME:    InvalidFileException
 * PURPOSE: To be thrown from FileIO up to the Controller
 *          when the file is deemed invalid. 
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.exceptions;

public class InvalidFileException extends Exception
{
    /**
     * Standard exception constructor.
     */
    public InvalidFileException(String msg, Throwable cause)
    {
        super(("Invalid input file: " + msg), cause);
    }

    public InvalidFileException(String msg)
    {
        super("Invalid input file: " + msg);
    }
}