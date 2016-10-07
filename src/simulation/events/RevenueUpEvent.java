/***
 * NAME:    RevenueUpEvent
 * PURPOSE: Event behaviour to increase the revenue of a business unit by 5%.
 * AUTHOR:  Luke Healy
 * DATE:    4/10/16
 */

package simulation.events;

import simulation.controller.Simulation;
import simulation.controller.exceptions.InvalidEventException;
import simulation.model.Property;
import simulation.model.BusinessUnit;

public class RevenueUpEvent implements Eventful
{
    private String propertyName;

    public RevenueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    /**
     * Grabs the property object and sets it's revenue to 105% of itself.
     */
    @Override
    public void doEvent(Simulation sim) throws InvalidEventException
    {
        Property property = sim.resolveProperty(propertyName);
        BusinessUnit b;
        
        if((b = property.isBusinessUnit()) != null)
        {
            b.setRevenue(b.getRevenue() * 1.05);
        }
        else
        {
            throw new InvalidEventException(
                "You can only increase the revenue of a Business Unit.");
        }
    }
}