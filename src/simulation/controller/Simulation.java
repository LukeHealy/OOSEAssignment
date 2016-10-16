/***
 * NAME:    Simulation
 * PURPOSE: Contains logic and necessary information regarding the simulation.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import java.io.FileNotFoundException;
import simulation.model.*;
import simulation.controller.exceptions.*;
import simulation.view.Output;
import simulation.observer.*;

public class Simulation implements Subject
{
    private int startYear;
    private int endYear;
    private Company primaryCompany;
    private List<Observer> observers;

    /* Object of 3 containers, properties, plans and events.
     * They hold all the data read from the input files.
     * A container was used so that the parser
     * nominated by the factory is able to handle writing to any of
     * the file data containers. Otherwise readFile in FileIO would have to know what 
     * type of file it's reading and pass the appropriate container, defeating
     * the purpose of the parser factory. 
     * Also, This inherantly solves the issue
     * of not knowing which command line argument is which file because
     * we don't care. The parser knows what to do.
     */
    private FileData fileData;

    /*
     * Store hashmap of properties, This is so that proprty names can easily 
     * be matched with properties for use thorughout the program.
     */
    private Map<String,Property> properties;

    public Simulation(FileData fileData, int startYear, int endYear, Company primaryCompany)
    {
        this.startYear = startYear;
        this.endYear = endYear;
        this.fileData = fileData;
        this.primaryCompany = primaryCompany;

        /* 
         * Can't inject this dependancy as it is populated later by
         * the observers themselves.
         */
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
     * The simulation has all of the necessary information from the files etc.
     * It doesn't yet know what to do with it however.
     * loadData is used to take the objects stored in fileData fills in any gaps.
     * This is done here because we can't do it while constructing the objects in
     * fileData as we may not have all the needed information as we construct
     * each object. Doing it here means we have all the needed information.
     */
    public void loadData() throws CouldNotLoadDataException
    {
        try
        {
            registerOwnersInCompanies();
            registerPropertiesInCompanies();
            registerPropertiesInPlans();
            checkOwnershipSanity();
            registerBusinessUnitObservers();
        }
        catch(BadOwnershipException | InvalidPlanException e)
        {
            throw new CouldNotLoadDataException(e.getMessage(), e);
        }
    }

    /**
     * Does the actual simulation logic. For each year, do the appropriate actions.
     */
    public void doSimulation() throws SimulationLogicErrorException
    {
        Output.printHeading();
        List<Property> properties =  new ArrayList<Property>(
            fileData.getProperties().values());
        List<Event> events = fileData.getEvents();
        List<Plan> plans = fileData.getPlans();
        List<Company> companies = null;

        try
        {
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
                Output.output(year, companies);
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
                        pl.doTransaction();
                        checkOwnershipSanity();
                    }
                }
            }
            Output.printFormatLine();
        }
        catch(BadOwnershipException | InvalidPlanException e)
        {
            throw new SimulationLogicErrorException(e.getMessage());
        }
    }

    /**
     * Used to update the bank balances of each company.
     */
    private void updateBankBalances(List<Company> companies)
    {
        for(Company c : companies)
        {
            c.updateBankBalance();
        }
    }

    /**
     * Goes through each business unit and registers it as an observer.
     */ 
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
    @Override
    public void attach(Observer observer)
    {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(double wage)
    {
        for(Observer o : observers)
        {
            o.update(wage);
        }
    }

    /**
     * Gives the observers the new wage value. Called by an event.
     */ 
    public void setWageChange(double wages)
    {
        notifyObservers(wages);
    }

    /**
     * Every property is contructed with an owner name (String), but because
     * we construct properties before all other properties are known, we must 
     * provide them with thier owner (company) object after they are constructed.
     * This method does exactly that. null is provided to properties with
     * unnamed owners.
     * Could not load data will be caught in main.
     */
    private void registerOwnersInCompanies() throws CouldNotLoadDataException
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
        }
    }

    /**
     * Gives each plan it's property object, based on it's propertyName.
     */
    private void registerPropertiesInPlans() throws InvalidPlanException{
        for(Plan p : fileData.getPlans())
        {
            Property current;
            if((current = resolveProperty(p.getPropertyName())) != null)
            {
                p.setProperty(current);
            }
            else
            {
                throw new InvalidPlanException(
                    "A plan involves a property that doesn't exist.");
            }
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
        List<Property> properties =  new ArrayList<Property>(
            fileData.getProperties().values());
        Set<Property> seenOwners;
        Property current;

        for(Property p : properties)
        {
            seenOwners = new HashSet<Property>();

            current = p.getOwner();
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
}






