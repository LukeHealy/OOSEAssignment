package simulation.controller;

import java.util.*;
import java.io.FileNotFoundException;

import simulation.model.FileData;
import simulation.controller.fileio.*;
import simulation.controller.exceptions.CouldNotLoadDataException;

public class Controller
{
    public static void main(String[] args)
    {   
        try
        {
            if(args.length < 5)
            {
                System.err.println("Not enough arguments");
                System.exit(1);
            }

            int startYear = Integer.parseInt(args[3]);
            int endYear =Integer.parseInt(args[4]);

            if(endYear < startYear)
            {
                throw new IllegalArgumentException("Start year must be less than end year.");
            }

            // Read files into fileData.
            FileData fileData = new FileData();
            FileIO fileIO = new FileIO(fileData);
            fileIO.readFiles(args);

            // Create simulation.
            Simulation sim = new Simulation(fileData, startYear, endYear);

            // Init and start simulation.
            sim.loadData();
            sim.doSimulation();
        }
        catch(CouldNotLoadDataException e)
        {
            System.err.println("Fatal Error: " + e.getMessage());
        }
        catch(NumberFormatException e4)
        {
            System.err.println("Fatal Error: Start and end years must be integers.");
        }
        catch(IllegalArgumentException e3)
        {
            System.err.println("Fatal Error: " + e3.getMessage());
        }
    }
}