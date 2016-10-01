import java.util.ArrayList;
import java.util.List;


/***
 * File parser factory
 */

public interface Parser
{
    public ArrayList parseFile(ArrayList<String> propertyString) throws InvalidFileException;
    public void populate(ArrayList toPopulate, ArrayList[] attributes);
}