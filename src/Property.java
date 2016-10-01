/***
 * NAME:    Property
 * PURPOSE: Base class for properties.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public abstract class Property
{
    /*
     * Keep the owners name as a seperate field from the owner
     * incase the owner is "unnamed". We do want a reference to the 
     * owner should there be one, so that ownership sanity can be checked.
     * Also so that the owner can be mapped to the property after all
     * properties have been read in. Otherwise we may have had to set the owner
     * to a company that doesn't exist yet.
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
        if(owner != null)
        {
            ownerName = owner.getName();
        }
        else
        {
            ownerName = "unnamed";
        }
    }

    public Company getOwner()
    {
        return this.owner;
    }

    public double getMonetaryValue()
    {
        return this.monetaryValue;
    }

    public double getProfit()
    {
        return profit;
    }

}
