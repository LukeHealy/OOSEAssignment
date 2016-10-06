package simulation.model;

import java.util.*;

public class FileData
{
    private HashMap<String,Property> properties;
    private ArrayList<Plan> plans;
    private ArrayList<Event> events;

    public FileData()
    {
        properties = new HashMap<String,Property>();
        plans = new ArrayList<Plan>();
        events = new ArrayList<Event>();
    }

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