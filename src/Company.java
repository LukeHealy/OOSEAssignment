/***
 * NAME:    Company
 * PURPOSE: Stores a company and it's required functionality and information.
 * AUTHOR:  Luke Healy
 * DATE:    30/11/16
 */

import java.util.HashSet;
import java.util.Iterator;

public class Company extends Property
{
    private String name;
    private BankAccount bank;

    // Set of owned properties.
    private HashSet<Property> properties;

    public Company(String ownerName, String name, double monetaryValue)
    {
        super(ownerName, monetaryValue);
        this.name = name;

        // The single bank account.
        bank = new BankAccount(this.name, 0.0 );

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

        while(p.hasNext())
        {
            totalProfits+= p.next().getProfit();
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
        properties.remove(prop);
    }

    //Wrapper for BankAccount.addToBalance().
    public void changeBankBalance(double amount)
    {
        bank.addToBalance(amount);
    }

    public String getName()
    {
        return this.name;
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
}