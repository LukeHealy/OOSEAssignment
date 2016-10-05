/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    4/10/16
 */

import java.util.ArrayList;
import java.util.List;

/***
 * File parser factory
 */

public interface Parser
{
    public void parseFile(ArrayList<String> file, FileData fileData) throws InvalidFileException;
}