/***
 * NAME:    BusinessUnit
 * PURPOSE: Stores a BusinessUnit. Each business unit is also an observer
            So that wages can be updated.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.model;

import simulation.observer.Observer;
import simulation.controller.Simulation;
import simulation.observer.Subject;

public class BusinessUnit extends Property implements Observer
{
    private double wages;
    private double revenue;
    private Subject simulation;

    public BusinessUnit(String ownerName, String name, 
        double monetaryValue, double wages, double revenue)
    {
        super(ownerName, monetaryValue);

        this.name = name;
        this.revenue = revenue;
        this.wages = wages;
    }

    /**
     * The simulation calls this an passes itself. The business unit
     * then calls attach in the simulation to attach itself as an observer.
     */
    public void attachSubject(Simulation sim)
    {
        sim.attach(this);
    }

    @Override
    public void calcProfit()
    {
        profit = revenue - wages;
    }

    public double getRevenue()
    {
        return revenue;
    }

    public void setRevenue(double revenue)
    {
        this.revenue = revenue;
    }

    // For debugging.
    @Override
    public String toString()
    {
        return ("Business unit: " + name + ", " + ownerName + ", " + monetaryValue + ", " + wages + ", " + revenue);
    }

    /**
     * Observer action is to update the wages.
     */
    @Override
    public void update(double change)
    {
        wages*= change;
    }

    public BusinessUnit isBusinessUnit()
    {
        return this;
    }

    public Company isCompany()
    {
        return null;
    }

}





