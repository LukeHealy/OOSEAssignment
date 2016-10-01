/***
 * NAME:    Plan
 * PURPOSE: Used to store a step of the plan. i.e a line of the plan file.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public class Plan
{
    /*
     * Keep references to the primary company and the relevant property
     * so that they can be modified by this plan.
     */
    private Company primaryCompany = null;
    private String propertyName;
    // Defined as the plan is executed.
    private Property property;

    /*
     * Transaction behaviour is defined at runtime, on construction. 
     * This is so that thesimulation doesn't have to be concerned with
     * what type (buy or sell) of plan is being carried out.
     */
    private Transaction transactionBehaviour;

    public Plan(String propertyName, Transaction transactionBehaviour)
    {
        this.propertyName = propertyName;
        this.transactionBehaviour = transactionBehaviour;
    }

    // May never be used.
    public void setTransactionBehaviour(Transaction transactionBehaviour)
    {
        this.transactionBehaviour = transactionBehaviour;
    }

    // Do the thing.
    public void doTransaction()
    {
        transactionBehaviour.transact(primaryCompany, property);
    }

    public void registerPrimaryCompany(Company primary)
    {
        if(this.primaryCompany == null)
        {
            this.primaryCompany = primary;
        }
    }

    public String toString()
    {
        return ("Plan: " + transactionBehaviour + ", " + propertyName);
    }

}