import java.util.*;
import java.io.FileNotFoundException;

public class Controller
{
    public static void main(String[] args)
    {   
        try
        {
            // read files into filedata
            FileData fileData = new FileData();
            FileIO fileIO = new FileIO(fileData);
            fileIO.readFiles(args);

            Simulation sim = new Simulation(fileData, Integer.parseInt(args[3]), Integer.parseInt(args[4]));

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