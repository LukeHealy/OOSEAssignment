/***
 * NAME:    BankAccount
 * PURPOSE: Stores a companies bank account. monetaryValue is the balance.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.model;

public class BankAccount extends Property
{

    public BankAccount(String ownerName, double monetaryValue)
    {
        super(ownerName, monetaryValue);
        name = null;
    }

    @Override
    public void calcProfit()
    {
        profit = monetaryValue * 0.05;
    }

    /**
     * Used when the companies bank account balance needs to be changed.
     * Just pass it a negative number to subtract from the balance.
     */
    public void addToBalance(double amount)
    {
        setMonetaryValue(monetaryValue+= amount);
    }

    public double getBalance()
    {
        return monetaryValue;
    }

    // For debugging.
    @Override
    public String toString()
    {
        return ("bank: " + ownerName + ", " + monetaryValue);
    }

    public BusinessUnit isBusinessUnit()
    {
        return null;
    }
    public Company isCompany()
    {
        return null;
    }
}