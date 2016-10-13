/***
 * NAME:    Event
 * PURPOSE: Used to store an unplanned event. i.e a line of the event file.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */
package simulation.model;

import simulation.events.Eventful;
import simulation.controller.Simulation;
import simulation.controller.exceptions.InvalidEventException;
import simulation.controller.exceptions.SimulationLogicErrorException;

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

    public Event(int year, String propertyName)
    {
        this.propertyName = propertyName;
        this.year = year;
    }

    public int getYear()
    {
        return year;
    }

    /**
     * Used to define the behavious of the event.
     */
    public void setEventBehaviour(Eventful eventBehaviour)
    {
        this.eventBehaviour = eventBehaviour;
    }

    /**
     * Do the event.
     */
    public void doEvent(Simulation sim) throws SimulationLogicErrorException
    {
        try
        {
            eventBehaviour.doEvent(sim);
        }
        catch(InvalidEventException e)
        {
            throw new SimulationLogicErrorException(e.getMessage());
        }
    }

    // Setters
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