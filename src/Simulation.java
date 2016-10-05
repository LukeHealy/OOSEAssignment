/***
 * NAME:    Simulation
 * PURPOSE: Contains logic and necessary information regarding the simulation.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

import java.util.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Simulation
{
    private final DecimalFormat formatter = new DecimalFormat("#0.00"); 
    private final String formatLine = "+------+-----------------------+------------------+";

    private int startYear;
    private int endYear;
    private Company primaryCompany;
    private List<Observer> observers;

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

    public Simulation(FileData fileData, int startYear, int endYear)
    {
        this.startYear = startYear;
        this.endYear = endYear;
        // Init the ArrayLists.
        this.fileData = fileData;

        primaryCompany = null;

        observers = new ArrayList<Observer>();
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
     * loadData takes the three file names and loads their contents into
     * the appropriate containers. If there is an issue with the files,
     * it throws an exception up to main which will ensure the program
     * quits safely.
     */
    public void loadData() throws CouldNotLoadDataException
    {
        try
        {
            registerPrimaryCompany();
            registerOwners();
            registerPropertiesInCompanies();
            registerPropertiesInPlans();
            checkOwnershipSanity();
            registerBusinessUnitObservers();
        }
        catch(BadOwnershipException e)
        {
            throw new CouldNotLoadDataException(e.getMessage(), e);
        }
    }

    public void doSimulation()
    {
        printHeading();
        ArrayList<Property> properties =  new ArrayList<Property>(
            fileData.getProperties().values());
        ArrayList<Event> events = fileData.getEvents();
        ArrayList<Plan> plans = fileData.getPlans();
        ArrayList<Company> companies = null;

        for(int year = startYear; year <= endYear; year++)
        {
            companies = new ArrayList<Company>();

            Company current;

            for(Property p : properties) // For each company
            {
                if((current = p.isCompany()) != null)
                {
                    companies.add(current);
                }
            }
            // Update bank account according to profit
            if(year > startYear)
            {
                updateBankBalances(companies);
            }
            // Output year, company name and bank balance
            output(year, companies);
            // Do events for this year
            for(Event e : events)
            {
                if(e.getYear() == year)
                {
                    //System.out.println(e.toString());
                    e.doEvent(this);
                }
            }
            // Do plans for this year
            for(Plan pl : plans)
            {
                if(pl.getYear() == year)
                {
                    //System.out.println(pl.toString());
                    pl.doTransaction();
                }
            }
        }
        output(endYear + 1, companies);            

        System.out.println(formatLine);
    }

    public void printHeading()
    {
        System.out.println(formatLine);
        System.out.println("| Year | Company               | Bank Balance     |");
    }

    private void output(int year, ArrayList<Company> companies)
    {
        System.out.println(formatLine);
        for(Company c : companies)
        {
            double balance = c.getBankBalance();
            System.out.printf(
                "| %-5d| %-22s| $%-16s|\n", year, c.getName(), 
                (balance > 0.0 ? formatter.format(balance) : "(" + formatter.format(balance) + ")") );
        }
    }

    private void updateBankBalances(ArrayList<Company> companies)
    {
        for(Company c : companies)
        {
            c.updateBankBalance();
        }
    }

    private void registerBusinessUnitObservers()
    {
        BusinessUnit current;
        for(Property p : fileData.getProperties().values())
        {
            if((current = p.isBusinessUnit()) != null)
            {
                current.attachSubject(this);
            }
        }
    }

    /**
     * Observers for wage changes.
     */
    public void attach(Observer observer)
    {
        this.observers.add(observer);
    }
 
    public void setWageChange(double wages)
    {
        notifyObservers(wages);
    }

    public void notifyObservers(double wage)
    {
        for(Observer o : observers)
        {
            o.update(wage);
        }
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

            //System.out.println("Set owner of '" + p.getName() + "' to '" + p.getOwnerName() + "'");
        }
    }

    private void registerPropertiesInPlans(){
        for(Plan p : fileData.getPlans())
        {
            p.setProperty(resolveProperty(p.getPropertyName()));
        }
    }

    /**
     * Used to attach the set of properties owned by a company to that company.
     */
    private void registerPropertiesInCompanies()
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
                        //System.out.println("Added " + p2.getName() + " to " + current.getName());
                    }
                }
            }
        }
    }

    /**
     * Cycles through all properties and get it's owner, then 
     * gets the owners owner etc until null is reached.
     * Record each owner as it goes and if a duplicate is seen,
     * throw an exception. The iteration will only ever end in null,
     * or find a duplicate owner.
     */
    private void checkOwnershipSanity() throws BadOwnershipException
    {
        ArrayList<Property> properties =  new ArrayList<Property>(
            fileData.getProperties().values());
        HashSet<Property> seenOwners;
        Property current;

        for(Property p : properties)
        {
            seenOwners = new HashSet<Property>();

            current = p;
            while(current != null)
            {

                if(!seenOwners.add(current))
                {
                    throw new BadOwnershipException(
                        "Cyclic ownership detected.");
                }
                current = current.getOwner();
            }
        }
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
    }
}






