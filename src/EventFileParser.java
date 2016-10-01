import java.util.ArrayList;
import java.util.List;

public class EventFileParser implements Parser
{
    @Override
    public void parseFile(ArrayList<String> eventFile, FileData fileDate) throws InvalidFileException
    {   
        int year;
        int previousYear = 0;
        String property;

        ArrayList<Event> events = new ArrayList<Event>();

        for(String p : eventFile)
        {
            Event event;

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
                        event.setEventBehaviour(new WageUpEvent());
                        break;
                    case "W+":
                        event.setEventBehaviour(new WageDownEvent());
                        break;
                    case "V+":
                        event.setEventBehaviour(new ValueUpEvent());
                        break;
                    case "V-":
                        event.setEventBehaviour(new ValueDownEvent());
                        break;
                    case "R-":
                        event.setEventBehaviour(new RevenueUpEvent(property));
                        break;
                    case "R+":
                        event.setEventBehaviour(new RevenueDownEvent());
                        break;
                    default:
                        throw new InvalidFileException("An event has an invalid type.");
                        break;
                }
            }
            else
            {
                throw new InvalidFileException("An event is missing a type.");
            }
            events.add(event);
        }
        fileDate.events = events;
    }
}