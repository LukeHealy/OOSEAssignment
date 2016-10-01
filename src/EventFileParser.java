import java.util.ArrayList;
import java.util.List;

public class EventFileParser implements Parser
{
    @Override
    public List<Property> parseFile(ArrayList<String> propertyStrings) throws InvalidFileException
    {
        
    }

    /**
     * See populate in PropertyFileParser for rationale.
     */
    @Override
    public void populate(ArrayList toPopulate, ArrayList[] attributes)
    {
        attributes[1] = toPopulate;
    }
}