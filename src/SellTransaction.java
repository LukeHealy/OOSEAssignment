public class SellTransaction implements Transaction
{
    public SellTransaction(){}
    
    public void transact(Company primaryCompany, Property property)
    {
        // Add selling price to primary company's bank.
        primaryCompany.changeBankBalance(property.getMonetaryValue());

        // Remove from primary company.
        primaryCompany.removeProperty(property);

        // Set new owner to null.
        property.setOwner(null);
    }
}