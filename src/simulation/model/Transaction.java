/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.model;
import simulation.controller.exceptions.InvalidPlanException;

public interface Transaction
{
    public void transact(Company primaryCompany, Property property) throws InvalidPlanException;
}