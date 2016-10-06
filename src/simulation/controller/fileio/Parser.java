/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    4/10/16
 */
package simulation.controller.fileio;

import java.util.ArrayList;
import java.util.List;
import simulation.model.FileData;
import simulation.controller.exceptions.InvalidFileException;


/***
 * File parser factory
 */

public interface Parser
{
    public void parseFile(ArrayList<String> file, FileData fileData) throws InvalidFileException;
}