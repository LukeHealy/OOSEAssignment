/***
 * NAME:    BankAccount
 * PURPOSE: Stores a companies bank account. monetaryValue is the balance.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

public class BankAccount extends Property
{

    public BankAccount(String ownerName, double monetaryValue)
    {
        super(ownerName, monetaryValue);
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
        monetaryValue+= amount;
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
}