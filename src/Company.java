/***
 * NAME:    Company
 * PURPOSE: Stores a company and it's required functionality and information.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

import java.util.HashSet;
import java.util.Iterator;

public class Company extends Property
{
    private BankAccount bank;

    // Set of owned properties.
    private HashSet<Property> properties;

    public Company(String ownerName, String name, double monetaryValue)
    {
        super(ownerName, monetaryValue);
        this.name = name;
        properties = new HashSet<Property>();

        // The single bank account.
        bank = new BankAccount(this.name, monetaryValue);
        properties.add(bank);
    }

    /**
     * Used to calculate the total profit of this company.
     * For each property, get it's profit and sum them.
     */
    @Override
    public void calcProfit()
    {
        double totalProfits = 0.0;

        Iterator<Property> p = properties.iterator();
        Property current;
        while(p.hasNext())
        {
            current = p.next();
            current.calcProfit();
            totalProfits+= current.getProfit();
        }

        profit = totalProfits;
    }

    // properties container wrappers.
    public void addProperty(Property prop)
    {
        properties.add(prop);
    }

    public void removeProperty(Property prop)
    {
        if(!properties.remove(prop)) //Not in the set
        {
            throw new IllegalArgumentException("Property doesn't exist: ");
        }
    }

    public void updateBankBalance()
    {
        calcProfit();
        changeBankBalance(profit);
    }

    public double getBankBalance()
    {
        //return monetary value of bank
        return bank.getBalance();
    }

    // Wrapper for BankAccount.addToBalance().
    public void changeBankBalance(double amount)
    {
        bank.addToBalance(amount);
    }

    // Return the name and account balance for printing.
    public String print()
    {
        return (name + bank.getBalance());
    }

    
    // For debugging.
    @Override
    public String toString()
    {
        return ("Company: " + name + ", " + ownerName + ", " + monetaryValue);
    }

    public void printProperties()
    {
        Iterator<Property> p = properties.iterator();

        while(p.hasNext())
        {
            System.out.println(p.next().toString());
        }
    }
    public BusinessUnit isBusinessUnit()
    {
        return null;
    }
    public Company isCompany()
    {
        return this;
    }
}






