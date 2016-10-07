/***
 * NAME:    PlanFileParser
 * PURPOSE: To parse and validate the plan file.
 *          The parser validates the FILE, but not the plan.
 *          If the plan is not logical, i.e it pertains to a non-existant
 *          property, it will be dealt with by the simulation. The behaviour of a plan
 *          is defined here.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */
package simulation.controller.fileio;

import java.util.ArrayList;
import java.util.List;
import simulation.model.FileData;
import simulation.model.Plan;
import simulation.model.BuyTransaction;
import simulation.model.SellTransaction;
import simulation.controller.exceptions.InvalidFileException;


public class PlanFileParser implements Parser
{
    @Override
    public void parseFile(ArrayList<String> planfile, FileData fileData) throws InvalidFileException
    {
        int year;
        int previousYear = 0;
        String property;

        ArrayList<Plan> plans = new ArrayList<Plan>();

        for(String p : planfile)
        {
            String[] parts = p.split(",", -1); 

            // Get the year of the plan.
            if(!parts[0].equals(""))
            {   
                try
                {
                    year = Integer.parseInt(parts[0]);
                }
                catch(NumberFormatException e)
                {
                    throw new InvalidFileException(
                        "Plan File: Invalid year: ", e);
                }

                // Make sure it is in order.
                if(year < previousYear)
                {
                    throw new InvalidFileException(
                        "Plan File: The plans are not in chronological order.");
                }
                else
                {
                    previousYear = year;
                }
            }
            else
            {
                throw new InvalidFileException(
                    "Plan File: A plan is missing a year.");
            }

            // Get the plan name. Any non empty string is fine.
            if(!parts[2].equals(""))
            {
                property = parts[2];
            }
            else
            {
                throw new InvalidFileException(
                    "Plan File: A plan is missing a property.");
            }

            /**
             * Parse the type of plan last as this is where we decide on which
             * transactionBehaviour to give the plan.
             */
            if(!parts[1].equals(""))
            {
                // Define transaction behaviour.
                if(parts[1].equals("B"))
                {
                    plans.add(new Plan(year, property, new BuyTransaction()));
                }
                else if(parts[1].equals("S"))
                {
                    plans.add(new Plan(year, property, new SellTransaction()));
                }
                else
                {
                    throw new InvalidFileException(
                        "Plan File: A plan has an invalid type.");
                }
            }
            else
            {
                throw new InvalidFileException(
                    "Plan File: An plan is missing a type.");
            }
        }
        // Add the list of plans to the fileData object.
        fileData.setPlans(plans);
    }
}

