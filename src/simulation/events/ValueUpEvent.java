/***
 * NAME:    
 * PURPOSE: Increases the value of a property by 5%. 
 *          This is a transaction behaviour which belongs to a Plan.
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.events;

import simulation.controller.Simulation;
import simulation.model.Property;

public class ValueUpEvent implements Eventful
{
    private String propertyName;

    public ValueUpEvent(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public void doEvent(Simulation sim)
    {
        Property property = sim.resolveProperty(propertyName);
        property.setMonetaryValue(property.getMonetaryValue() * 1.05);
    }
}