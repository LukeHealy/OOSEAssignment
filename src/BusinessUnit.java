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

    @Override
    public void update()
    {
        this.wages = (double)simulation.getState();
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





