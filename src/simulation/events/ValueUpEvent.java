/***
 * NAME:    
 * PURPOSE: 
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