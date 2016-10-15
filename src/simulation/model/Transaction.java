/***
 * NAME:    Transaction
 * PURPOSE: Interface for a transaction behaviour.
 * AUTHOR:  Luke Healy
 * DATE:    2/10
 */

package simulation.model;
import simulation.controller.exceptions.InvalidPlanException;

public interface Transaction
{
    public void transact(Company primaryCompany, Property property) throws InvalidPlanException;
}