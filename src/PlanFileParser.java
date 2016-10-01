/***
 * NAME:    PlanFileParser
 * PURPOSE: To parse and validate the plan file. Also houses a 
 *          version of the populate method.
 *          Note: The parser validates the FILE, but not the plan.
 *          If the plan is not logical, i.e it pertains to a non-existant
 *          property, it will be dealt with by the simulation.
 * AUTHOR:  Luke Healy
 * DATE:    1/10/16
 */

import java.util.ArrayList;
import java.util.List;

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

            // Get the property name. Any non empty string is fine.
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
             * Do the type of plan last as this is where we decide on which
             * transactionBehaviour to give the plan.
             */
            if(!parts[1].equals(""))
            {
                if(parts[1].equals("B"))
                {
                    // Define transaction behaviour.
                    plans.add(new Plan(property, new BuyTransaction()));
                }
                else if(parts[1].equals("S"))
                {
                    // Define transaction behaviour.
                    plans.add(new Plan(property, new SellTransaction()));
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
        fileData.plans = plans;
    }
}

