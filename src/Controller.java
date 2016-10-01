import java.util.*;
import java.io.FileNotFoundException;

public class Controller
{
    public static void main(String[] args)
    {   
        Simulation sim = new Simulation();
        try
        {
            sim.setYears(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            sim.loadData(Arrays.copyOfRange(args, 0, 2));
        }
        catch(CouldNotLoadDataException e)
        {
            System.out.println("Fatal Error: " + e.getMessage());
        }
        //sim.startSimulation();
    }
}