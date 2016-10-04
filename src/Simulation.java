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

    /* Container of 3 containers, properties, plans and events.
     * They hold all the data read from the input files.
     * A container was used so that the parser
     * nominated by the factory is able to handle writing to any of
     * the file data containers. Otherwise Simulation would have to know what 
     * type of file it's reading and pass the appropriate container, defeating
     * the purpose of the parser factory. 
     * Also, This inherantly solves the issue
     * of not knowing which command line argument is which file because
     * we don't care! The parser knows what to do.
     */
    private FileData fileData;

    /*
     * Store hashmap of properties, even though there is already an arraylist.
     * This is so that proprty names can easily be matched with properties
     * for use thorughout the program.
     */
    private HashMap<String,Property> properties;

    public Simulation()
    {
        startYear = 0;
        endYear = 0;
        primaryCompany = null;

        // Init the ArrayLists.
        fileData = new FileData();

        observers = new ArrayList<Observer>();
        sim = this;
    }

    public Property resolveProperty(String propertyName)
    {
        return fileData.getProperties().get(propertyName);
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
            FileIO fileIO = new FileIO(fileData);

            for(int i = 0; i < 3; i++)
            {
                file = fileIO.readCSVFile(fileNames[i]);
                parser = fileIO.makeParser(file.get(0));
                // We can get rid of the header now.
                file.remove(0);
                parser.parseFile(file);
            }

            registerPrimaryCompany();
            System.out.println("");
            registerOwners();
            System.out.println("");
            registerProperties();
            System.out.println("");
            checkOwnershipSanity();
        }
        catch(FileNotFoundException | 
            InvalidFileException | BadOwnershipException e)
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

    public Company getPrimaryCompany()
    {
        return primaryCompany;
    }

    /**
     * Every property is contructed with an owner name (String), but because
     * we construct properties before all other properties are known, we must 
     * provide them with thier owner (company) object after they are constructed.
     * This method does exactly that. null is provided to properties with
     * unnamed owners.
     * Could not load data will be caught in main.
     */
    private void registerOwners() throws CouldNotLoadDataException
    {
        for(Property p : fileData.getProperties().values())
        {
            Company owner;
            String ownerName = p.getOwnerName();

            if(ownerName.equals("unnamed"))
            {
                owner = null;
            }
            else
            {
                try
                {
                    // Use isCompany to convert the property to a company.
                    if((owner = fileData.getProperties().get(ownerName).isCompany()) == null)
                    {
                        throw new CouldNotLoadDataException(
                            "Cannot register " + ownerName + " as an owner: Not a company.");
                    }
                }
                catch(NullPointerException e)
                {
                    throw new CouldNotLoadDataException(
                        "Cannot register " + ownerName + " as an owner: Doesn't exist.", e);
                }
            }
            p.setOwner(owner);

            System.out.println("Set owner of '" + p.getName() + "' to '" + p.getOwnerName() + "'");
        }
    }

    /**
     * Used to attach the set of properties owned by a company to that company.
     */
    private void registerProperties()
    {
        Company current;

        // For every property
        for(Property p : fileData.getProperties().values())
        {
            // If it's a company
            if((current = p.isCompany()) != null)
            {
                // Add every property where current is the owner.
                for(Property p2 : fileData.getProperties().values())
                {
                    if(p2.getOwner() != null && p2.getOwner().equals(current))
                    {
                        current.addProperty(p2);
                        System.out.println("Added " + p2.getName() + " to " + current.getName());
                    }
                }
            }
        }
    }

    private void checkOwnershipSanity() throws BadOwnershipException
    {
        ArrayList<Property> properties =  new ArrayList<Property>(fileData.getProperties().values());
        HashSet<Company> seenOwners ;
        Company current;

        for(Property p : properties)
        {
            seenOwners = new HashSet<Company>();

            if((current = p.isCompany()) != null)
            {
                System.out.println("\nOwnership list for " + current.getName() + ": ");
                while(current != null)
                {
                    System.out.print(current.getName() + " : ");

                    if(!seenOwners.add(current))
                    {
                        throw new BadOwnershipException(
                            "Cyclic ownership detected.");
                    }
                    current = current.getOwner();
                }
            }
        }
        System.out.println("");
    }

    /**
     * Sets the primary company to the first one in the hashmap (which
     * will be the first one in the file).
     */
    private void registerPrimaryCompany()
    {
        Iterator<Property> p = fileData.getProperties().values().iterator();

        /*
         * isCompany returns null until it gets a company. Do the loop until
         * a company is returned, this must be the first one, therefore the primary.
         */
        while((primaryCompany = p.next().isCompany()) == null );

        System.out.println("Primary company set to " + primaryCompany.getName());
    }
}






