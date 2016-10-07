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

import java.util.*;

public class FileData
{
    // Properties are mapped by name.
    private HashMap<String,Property> properties;
    private ArrayList<Plan> plans;
    private ArrayList<Event> events;

    public FileData()
    {
        properties = new HashMap<String,Property>();
        plans = new ArrayList<Plan>();
        events = new ArrayList<Event>();
    }

    // Getters and Setters
    public HashMap<String,Property> getProperties()
    {
        return properties;
    }

    public ArrayList<Plan> getPlans()
    {
        return plans;
    }

    public ArrayList<Event> getEvents()
    {
        return events;
    }

    public void setProperties(HashMap<String,Property> properties)
    {
        this.properties = properties;
    }

    public void setPlans(ArrayList<Plan> plans)
    {
        this.plans = plans;
    }

    public void setEvents(ArrayList<Event> events)
    {
        this.events = events;
    }
}