/***
 * NAME:    PropertyFileParser
 * PURPOSE: Parse and validate the property file. Stores the map of properties
 *          in the fileData object.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.fileio;

import java.util.ArrayList;
import java.util.HashMap;
import simulation.model.FileData;
import simulation.controller.exceptions.InvalidFileException;
import simulation.model.*;

public class PropertyFileParser implements Parser
{    
    @Override
    public void parseFile(ArrayList<String> propertyString, FileData fileData) throws InvalidFileException
    {
        HashMap<String,Property> properties = new HashMap<String,Property>();

        String ownerName;
        String name;
        Double monetaryValue;
        Double revenue;
        Double wages;
        
        for(String ps : propertyString)
        {
            String[] parts = ps.split(",", -1);

            // Add name. If empty throw exception.
            if(!parts[0].equals(""))
            {
                name = parts[0];
            }
            else {
                throw new InvalidFileException(
                    "Property file: A property is missing a name.");
            }

            // Add the owner name if one exists.
            if(!parts[2].equals(""))
            {
                ownerName = parts[2];
            }
            else
            {
                ownerName = "unnamed";
            }

            // Add monetary value. If empty throw exception.
            if(!parts[3].equals(""))
            {
                monetaryValue = Double.parseDouble(parts[3]);
            }
            else
            {
                throw new InvalidFileException(
                    "Property file: A property is missing a monetary value.");
            }

            // Add the company to the list. Otherwise finish adding 
            // to the business unit and add it.
            if(parts[1].equals("C"))
            {
                properties.put(name, new Company(ownerName, name, monetaryValue));
            }
            else if(parts[1].equals("B"))
            {
                // Add revenue.
                if(!parts[4].equals(""))
                {
                    revenue = Double.parseDouble(parts[4]);
                }
                else
                {
                    throw new InvalidFileException(
                        "Property file: A Business Unit is missing revenue.");
                }

                // Add wages.
                if(!parts[5].equals(""))
                {
                    wages = Double.parseDouble(parts[5]);
                }
                else
                {
                    throw new InvalidFileException(
                        "Property file: A Business Unit is missing wages.");
                }
                properties.put(name, new BusinessUnit(ownerName, name, monetaryValue, wages, revenue));
            }
            else
            {
                throw new InvalidFileException(
                    "Property file: '" + parts[1] + "' is not a valid type. Use 'C' or 'B'.");
            }
        }
        // Add the list of plans to the fileData object.
        fileData.setProperties(properties);
    }
}