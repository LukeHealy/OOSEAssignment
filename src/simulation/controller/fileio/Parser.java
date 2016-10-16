/***
 * NAME:    Parser
 * PURPOSE: Interface to define the API for a file parser.
 * AUTHOR:  Luke Healy
 * DATE:    4/10/16
 */
package simulation.controller.fileio;

import java.util.ArrayList;
import java.util.List;
import simulation.model.FileData;
import simulation.controller.exceptions.InvalidFileException;


/***
 * Goes through each line of the file and contructs the relevant objects
 * with the data from the file. These objects are stored in fileData.
 */

public interface Parser
{
    public void parseFile(List<String> file, FileData fileData) throws InvalidFileException;
}