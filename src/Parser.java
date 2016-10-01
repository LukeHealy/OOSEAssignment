import java.util.ArrayList;
import java.util.List;


/***
 * File parser factory
 */

public interface Parser
{
    public void parseFile(ArrayList<String> propertyString, FileData fileData) throws InvalidFileException;
}