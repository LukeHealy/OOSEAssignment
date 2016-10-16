/***
 * NAME:    Controller
 * PURPOSE: Entry point for the program. Initiates file reading, contructs the
 *          Simulator and passes it the information from the files, inside the 
 *          fileData object. Also passes the start and end years. Does some 
 *          very basic command line argument validation. Prints fatal errors 
 *          to the user.
 * AUTHOR:  Luke Healy
 * DATE:    
 */
package simulation.controller;

import java.util.*;
import java.io.FileNotFoundException;

import simulation.model.FileData;
import simulation.model.Company;
import simulation.model.Plan;
import simulation.model.Property;
import simulation.controller.fileio.*;
import simulation.controller.exceptions.CouldNotLoadDataException;
import simulation.controller.exceptions.SimulationLogicErrorException;

public class Controller
{
    public static void main(String[] args)
    {   
        try
        {
            // Check there are enough arguments provided.
            if(args.length < 5)
            {
                System.err.println("Not enough arguments.");
                System.exit(1);
            }

            int startYear = Integer.parseInt(args[3]);
            int endYear =Integer.parseInt(args[4]);

            // Not possible.
            if(endYear < startYear)
            {
                throw new IllegalArgumentException("Start year must be less than end year.");
            }

            // Read files into fileData.
            FileData fileData = new FileData();
            FileIO fileIO = new FileIO(fileData);
            // This provides elements 0, 1 and 2.
            fileIO.readFiles(Arrays.copyOfRange(args, 0, 3));

            // Register primary comoany in plans and get a reference
            // to give to the simulation.
            Company primary = registerPrimaryCompany(fileData);

            // Create simulation.
            Simulation sim = new Simulation(fileData, startYear, endYear, primary);

            // Init and start simulation.
            sim.loadData();
            sim.doSimulation();
        }
        catch(CouldNotLoadDataException e)
        {
            System.err.println("Fatal Error: " + e.getMessage());
            System.exit(1);    
        }
        catch(SimulationLogicErrorException e2)
        {
            System.err.println(e2.getMessage());
        }
        catch(NumberFormatException e4)
        {
            System.err.println("Fatal Error: Start and end years must be integers.");
            System.exit(1);
        }
        catch(IllegalArgumentException e3)
        {
            System.err.println("Fatal Error: " + e3.getMessage());
            System.exit(1);
        }
        
        // Everything went fine.
        System.exit(0);
    }
    
    /**
     * Sets the primary company to the first one in the hashmap (which
     * will be the first one in the file). Throws CouldNotLoadDataException
     * if it can't find a company in the set of properties.
     */
    private static Company registerPrimaryCompany(FileData fileData) throws CouldNotLoadDataException
    {
        Iterator<Property> p = fileData.getProperties().values().iterator();

        /*
         * isCompany returns null until it gets a company. Do the loop until
         * a company is returned, this must be the first one, therefore the primary.
         */
        Company primaryCompany = null;
        try
        {
            while((primaryCompany = p.next().isCompany()) == null );

            // Register primary company with each plan.
            for(Plan pl : fileData.getPlans())
            {
                pl.registerPrimaryCompany(primaryCompany);
            }
        }
        catch(NoSuchElementException e)
        {
            throw new CouldNotLoadDataException(
                "Must specify at least 1 Company.");
        }
        return primaryCompany;
    }
}