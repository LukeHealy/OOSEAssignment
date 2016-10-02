public interface Transaction
{
    public void transact(Company primaryCompany, Property property) throws InvalidPlanException;
}