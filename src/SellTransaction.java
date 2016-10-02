/***
 * NAME:    BuyTransaction
 * PURPOSE: Contains the transaction behaviour for selling a property.
            Used by a Plan.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */

public class SellTransaction implements Transaction
{
    public SellTransaction(){}

    public void transact(Company primaryCompany, Property property) throws InvalidPlanException
    {
        try
        {
            // Remove from primary company.
            primaryCompany.removeProperty(property);
            
            // Add selling price to primary company's bank.
            primaryCompany.changeBankBalance(property.getMonetaryValue());

            // Set new owner to null.
            property.setOwner(null);
        }
        catch(IllegalArgumentException e)
        {
            throw new InvalidPlanException(e.getMessage() + 
                "Cannot sell a property that is not owned.");
        }
    }
}