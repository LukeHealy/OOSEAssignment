/***
 * NAME:    Plan
 * PURPOSE: Used to store a step of the plan. i.e a line of the plan file.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

package simulation.model;

import simulation.controller.Simulation;
import simulation.controller.exceptions.InvalidPlanException;

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
    private int year;
    private Simulation sim;

    /*
     * Transaction behaviour is defined at runtime, on construction. 
     * This is so that thesimulation doesn't have to be concerned with
     * what type (buy or sell) of plan is being carried out.
     */
    private Transaction transactionBehaviour;

    public Plan(int year, String propertyName, Transaction transactionBehaviour)
    {
        this.year = year;
        this.propertyName = propertyName;
        this.transactionBehaviour = transactionBehaviour;
    }

    // May never be used.
    public void setTransactionBehaviour(Transaction transactionBehaviour)
    {
        this.transactionBehaviour = transactionBehaviour;
    }

    public void setProperty(Property property){
        this.property = property;
    }

    // Do the thing.
    public void doTransaction() throws InvalidPlanException
    {
        transactionBehaviour.transact(primaryCompany, property);
    }

    /**
     * Used to give a plan a reference to the primary company.
     */
    public void registerPrimaryCompany(Company primary)
    {
        if(this.primaryCompany == null)
        {
            this.primaryCompany = primary;
        }
    }

    public void mapProperty(Property property)
    {
        this.property = property;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public int getYear()
    {
        return year;
    }

    public String toString()
    {
        return ("Plan: " + year + ", " + transactionBehaviour + ", " + propertyName);
    }

}