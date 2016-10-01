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
    private Company primaryCompany = null;

    public Event(int year, String propertyName, Eventful eventBehaviour)
    {
        this.propertyName = propertyName;
        this.year = year;
        this.eventBehaviour = eventBehaviour;
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

    public void doEvent()
    {
        eventBehaviour.doEvent();
    }

    public void registerPrimaryCompany(Company primaryCompany)
    {
        if(this.primaryCompany == null)
        {
            this.primaryCompany = primaryCompany;
        }
    }

    public String toString()
    {
        return ("Event: " + year + eventBehaviour + propertyName);
    }
}