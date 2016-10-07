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

            // Create simulation.
            Simulation sim = new Simulation(fileData, startYear, endYear);

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
}