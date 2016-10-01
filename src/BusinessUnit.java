/***
 * NAME:    BusinessUnit
 * PURPOSE: Stores a BusinessUnit.
 * AUTHOR:  Luke Healy
 * DATE:    30/11/16
 */

public class BusinessUnit extends Property
{
    private double wages;
    private double revenue;
    private String name;

    public BusinessUnit(String ownerName, String name, 
        double monetaryValue, double wages, double revenue)
    {
        super(ownerName, monetaryValue);

        this.name = name;
        this.revenue = revenue;
        this.wages = wages;

    }

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
}