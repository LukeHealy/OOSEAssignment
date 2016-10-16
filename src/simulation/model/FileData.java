/***
 * NAME:    FileData
 * PURPOSE: Holds the three contianers which contain the parsed data from input
 *          files. Used so that the data can be cleanly passed to the 
 *          Simulation. Also used so that the file parser can put its result 
 *          into a common object, meaning the call to the parser is common for
 *          any of the files.
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FileData
{
    // Properties are mapped by name.
    private Map<String,Property> properties;
    private List<Plan> plans;
    private List<Event> events;

    public FileData()
    {
        properties = new HashMap<String,Property>();
        plans = new ArrayList<Plan>();
        events = new ArrayList<Event>();
    }

    // Getters and Setters
    public Map<String,Property> getProperties()
    {
        return properties;
    }

    public List<Plan> getPlans()
    {
        return plans;
    }

    public List<Event> getEvents()
    {
        return events;
    }

    public void setProperties(Map<String,Property> properties)
    {
        this.properties = properties;
    }

    public void setPlans(List<Plan> plans)
    {
        this.plans = plans;
    }

    public void setEvents(List<Event> events)
    {
        this.events = events;
    }
}