/***
 * NAME:    Simulation
 * PURPOSE: Contains logic and necessary information regarding the simulation.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

import java.util.*;
import java.io.FileNotFoundException;

public class Simulation implements Subject
{
    private int startYear;
    private int endYear;
    private Company primaryCompany;
    private List<Observer> observers;
    private double wages;

    /*
     * Hold a static reference to the instance. NOTE: this is not a singleton.
     * This reference is overwitten with the new instance when one is constructed.
     * It is needed so that observers can get the instance of the subject.
     */
    private static Simulation sim = null;

    /* Array of 3 ArrayLists, properties, plans and events.
     * They hold all the data read from the input files.
     * An array was used over explicit fields so that the parser
     * nominated by the factory is able to handle writing to any of
     * the ArrayLists. Otherwise Simulation would have to know what 
     * type of file it's reading and pass the appropriate list, defeating
     * the purpose of the parser factory. This inherantly solves the issue
     * of not knowing which command line argument is which file because
     * we don't care! The parser knows what to do.
     */
    private ArrayList[] fileData;

    public Simulation()
    {
        startYear = 0;
        endYear = 0;
        primaryCompany = null;

        // Init the ArrayLists.
        fileData = new ArrayList[3];
        fileData[0] = new ArrayList<Property>();
        fileData[1] = new ArrayList<Plan>();
        fileData[2] = new ArrayList<Event>();

        observers = new ArrayList<Observer>();
        sim = this;
    }

    /**
     * Called from main, sets the years that the simulation will work with.
     */ 
    public void setYears(int startYear, int endYear)
    {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Basically a work around for the fact that the observer that needs a
     * reference to this instance is contructed elsewhere. It's constructor
     * calls this method which returns the static reference to the instance.
     * Again, not a singleton.
     */ 
    public static Simulation getSimulationInstance()
    {
        return sim;
    }

    /**
     * loadData takes the three file names and loads their contents into
     * the appropriate containers. If there is an issue with the files,
     * it throws an exception up to main which will ensure the program
     * quits safely.
     */
    public void loadData(String[] fileNames) throws CouldNotLoadDataException
    {
        try
        {
            ArrayList<String> file;
            Parser parser;

            for(int i = 0; i < 3; i++)
            {
                System.out.println(i); // Remove

                file = FileIO.readCSVFile(fileNames[i]);
                parser = FileIO.makeParser(file.get(0));
                // We can get rid of the header now.
                file.remove(0);
                parser.populate(parser.parseFile(file), fileData);

                //setPrimaryCompany();
                for(Object p : fileData[i])
                {
                    System.out.println(p.toString());
                }
            }
        }
        catch(FileNotFoundException | InvalidFileException e)
        {
            throw new CouldNotLoadDataException(e.getMessage(), e);
        }
    }


    /**
     * Observers for wage changes.
     */
    public void setWage(double wages)
    {
        this.wages = wages;
        notifyObservers();
    }
    public void attach(Observer observer)
    {
        this.observers.add(observer);
    }

    public void notifyObservers()
    {
        for(Observer o : observers)
        {
            o.update();
        }
    }

    public Double getState()
    {
        return this.wages;
    }


    /**
     * Sets the primary company to the first one listed in the property file.
     */
    private void setPrimaryCompany(ArrayList<String> propertyList)
    {
        
    }
}