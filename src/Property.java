/***
 * NAME:    Property
 * PURPOSE: Base class for properties.
 * AUTHOR:  Luke Healy
 * DATE:    30/11/16
 */

public abstract class Property
{
    /*
     * Keep the owners name as a seperate field from the owner
     * incase the owner is "unnamed". We do want a reference to the 
     * owner should there be one, so that ownership sanity can be checked.
     */
    protected Company owner = null;
    protected String ownerName;
    protected double monetaryValue;
    protected double profit;

    public Property(String ownerName, double monetaryValue)
    {
        this.ownerName = ownerName;
        this.monetaryValue = monetaryValue;
    }

    public abstract void calcProfit();
    public abstract String toString();
    
    /**
     * Used to set a new owner when bought/sold.
     * Used to set initial owner.
     */
    public void setOwner(Company owner)
    {
        this.owner = owner;
    }

    public double getProfit()
    {
        return profit;
    }

}
