/***
 * NAME:    Event
 * PURPOSE: Used to store an unplanned event. i.e a line of the event file.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */

public class Event
{
    private int year;
    private Eventful eventBehaviour;
    private String propertyName;
    private Property property;

    public Event(int year, String propertyName, Eventful eventBehaviour)
    {
        this.propertyName = propertyName;
        this.year = year;
        this.eventBehaviour = eventBehaviour;
    }

    public int getYear()
    {
        return year;
    }

    public Event(int year, String propertyName)
    {
        this.propertyName = propertyName;
        this.year = year;
    }

    public void setEventBehaviour(Eventful eventBehaviour)
    {
        this.eventBehaviour = eventBehaviour;
    }

    public void doEvent(Simulation sim)
    {
        try
        {
            eventBehaviour.doEvent(sim);
        }
        catch(InvalidEventException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setProperty(Property property)
    {
        this.property = property;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public String toString()
    {
        return ("Event: " + year + ", " + eventBehaviour + ", " + propertyName);
    }
}