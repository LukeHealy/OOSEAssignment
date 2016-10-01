import java.util.*;

public class FileData
{
    public HashMap<String,Property> properties;
    public ArrayList<Plan> plans;
    public ArrayList<Event> events;

    public FileData()
    {
        properties = new HashMap<String,Property>();
        plans = new ArrayList<Plan>();
        events = new ArrayList<Event>();
    }
}