/***
 * NAME:    Simulation
 * PURPOSE: Contains logic and necessary information regarding the simulation.
 * AUTHOR:  Luke Healy
 * DATE:    30/11/16
 */

import java.util.*;
import java.io.FileNotFoundException;

public class Simulation
{
    private int startYear;
    private int endYear;
    private Company primaryCompany;

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
        // fileData[1] = new ArrayList<Plan>();
        // fileData[2] = new ArrayList<Event>();
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
                file = FileIO.readCSVFile(fileNames[i]);
                parser = FileIO.makeParser(file.get(0));
                file.remove(0);
                parser.populate(parser.parseFile(file), fileData);
            }

            //setPrimaryCompany(propFile);
            for(Object p : fileData[0])
            {
                System.out.println(p.toString());
            }
        }
        catch(FileNotFoundException | InvalidFileException e)
        {
            throw new CouldNotLoadDataException(e.getMessage(), e);
        }
    }

    /**
     * Sets the primary company to the first one listed in the property file.
     */
    private void setPrimaryCompany(ArrayList<String> propertyList)
    {
        
    }
}