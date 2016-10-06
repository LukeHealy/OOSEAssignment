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
            // Read files into fileData.
            FileData fileData = new FileData();
            FileIO fileIO = new FileIO(fileData);
            fileIO.readFiles(args);

            // Create simulation.
            Simulation sim = new Simulation(fileData, Integer.parseInt(args[3]), Integer.parseInt(args[4]));

            // Init and start simulation.
            sim.loadData();
            sim.doSimulation();
        }
        catch(CouldNotLoadDataException e)
        {
            System.out.println("Fatal Error: " + e.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException e2)
        {
            System.out.println("Not enough arguments.");
        }
    }
}