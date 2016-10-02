/***
 * NAME:    BuyTransaction
 * PURPOSE: Contains the transaction behaviour for buying a property.
            Used by a Plan.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */

public class BuyTransaction implements Transaction
{
    public BuyTransaction(){}

    public void transact(Company primaryCompany, Property property) throws InvalidPlanException
    {
        double sellingPrice = property.getMonetaryValue();

        // If owned by a known company.
        Company previousOwner;
        if((previousOwner = property.getOwner()) != null)
        {
            // Remove from that companies set of property.
            previousOwner.removeProperty(property);
            
            // Add the money to the previous company's bank.
            previousOwner.changeBankBalance(sellingPrice);
        }

        // Add to primary company.
        primaryCompany.addProperty(property);
        property.setOwner(primaryCompany);
        // Take selling price from bank.
        primaryCompany.changeBankBalance( -1.0 * sellingPrice);
    }
}