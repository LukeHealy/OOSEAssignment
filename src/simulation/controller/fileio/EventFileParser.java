/***
 * NAME:    
 * PURPOSE: Parse, validate and store the events file.
 * AUTHOR:  Luke Healy
 * DATE:    4/10/16
 */
package simulation.controller.fileio;

import java.util.ArrayList;
import java.util.List;
import simulation.model.FileData;
import simulation.controller.exceptions.InvalidFileException;
import simulation.model.Event;
import simulation.events.*;
import simulation.controller.Simulation;

public class EventFileParser implements Parser
{
    @Override
    public void parseFile(ArrayList<String> eventFile, FileData fileData) throws InvalidFileException
    {   
        int year;
        int previousYear = 0;
        String property;

        ArrayList<Event> events = new ArrayList<Event>();

        for(String p : eventFile)
        {
            Event event;

            if(p.equals(""))
            {
                System.out.println("empty");
            }

            String[] parts = p.split(",", -1); 

            // Get year.
            if(!parts[0].equals(""))
            {
                try
                {
                    year = Integer.parseInt(parts[0]);
                }
                catch(NumberFormatException e)
                {
                    throw new InvalidFileException(
                        "Event File: Invalid year.", e);
                }

                // Make sure it is in order.
                if(year < previousYear)
                {
                    throw new InvalidFileException(
                        "Event File: The events are not in chronological order.");
                }
                else
                {
                    previousYear = year;
                }
            }
            else
            {
                throw new InvalidFileException(
                    "Event File: An event is missing a year.");
            }

            // Get property name. If none set it to null.
            if(!parts[2].equals(""))
            {
                if(!parts[1].contains("W"))
                {
                    property = parts[2];
                }
                else
                {
                    throw new InvalidFileException(
                        "Event File: Unexpected property on wage event.");
                }
            }
            else
            {
                if(parts[1].contains("W"))
                {
                    property = null;
                }
                else
                {
                    throw new InvalidFileException(
                        "Event File: An event that requires a property doesn't have one.");
                }
            }

            event = new Event(year, property);

            // Get event type and define event behaviour.
            if(!parts[1].equals(""))
            {
                switch(parts[1])
                {
                    case "W-":
                        event.setEventBehaviour(new WageDownEvent());
                        break;
                    case "W+":
                        event.setEventBehaviour(new WageUpEvent());
                        break;
                    case "V+":
                        event.setEventBehaviour(new ValueUpEvent(property));
                        break;
                    case "V-":
                        event.setEventBehaviour(new ValueDownEvent(property));
                        break;
                    case "R-":
                        event.setEventBehaviour(new RevenueUpEvent(property));
                        break;
                    case "R+":
                        event.setEventBehaviour(new RevenueDownEvent(property));
                        break;
                    default:
                        throw new InvalidFileException("An event has an invalid type.");
                }
            }
            else
            {
                throw new InvalidFileException("An event is missing a type.");
            }
            events.add(event);
        }
        // Add events to fileData.
        fileData.setEvents(events);
    }
}