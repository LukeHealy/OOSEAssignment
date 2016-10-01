/***
 * NAME:    BusinessUnit
 * PURPOSE: Stores a BusinessUnit.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public class BusinessUnit extends Property implements Observer
{
    private double wages;
    private double revenue;
    private String name;
    private Subject simulation;

    public BusinessUnit(String ownerName, String name, 
        double monetaryValue, double wages, double revenue)
    {
        super(ownerName, monetaryValue);

        this.name = name;
        this.revenue = revenue;
        this.wages = wages;

        this.simulation = Simulation.getSimulationInstance();
        simulation.attach(this);
    }

    @Override
    public void calcProfit()
    {
        profit = revenue - wages;
    }

    public String getName()
    {
        return this.name;
    }

    // For debugging.
    @Override
    public String toString()
    {
        return ("Business unit: " + name + ", " + ownerName + ", " + monetaryValue + ", " + wages + ", " + revenue);
    }

    @Override
    public void update()
    {
        this.wages = (double)simulation.getState();
    }
}