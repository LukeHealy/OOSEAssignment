/***
 * NAME:    FileIO
 * PURPOSE: Contain all file input and output functionality.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */

import java.io.*;
import java.util.*;

public class FileIO
{
    private FileData fileData;

    public FileIO(FileData fileData)
    {
        this.fileData = fileData;
    }
    /**
     * Read any .csv file and return its contents as an arraylist of lines.
     */
    public ArrayList<String> readCSVFile(String path) throws FileNotFoundException
    {
        String line;
        ArrayList<String> file = new ArrayList<String>();
        FileInputStream fis = null;

        try
        {
            fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while((line = br.readLine()) != null)
            {
                file.add(line);
            }

            fis.close();
        }
        // We cannot deal with this here to rethrow.
        catch(FileNotFoundException e)
        {
            throw e;
        }
        // Some issue with the file so close it and tell the user.
        catch(IOException e2)
        {
            System.out.println(e2.getMessage());
            try
            {
                fis.close();
            }
            // Can't close the file.
            catch(IOException e3)
            {
                System.out.println("Can't close file.");
            }
        }
        return file;
    }

    /**
     * makeParser is the parse factory used by loadData in Simulation.
     * It looks at the file header and returns the appropriate file parser.
     * If the header is not recognizable, throw an exception.
     */
    public Parser makeParser(String fileType) throws InvalidFileException
    {
        Parser parser = null;

        if(fileType.equals("Name,Type,Owner,Worth,Revenue,Wages"))
        {
            parser = new PropertyFileParser(fileData);
        }
        else if(fileType.equals("Year,Event,Property"))
        {
            parser = new EventFileParser(fileData);
        }
        else if(fileType.equals("Year,Buy/Sell,Property"))
        {
            parser = new PlanFileParser(fileData);
        }
        else
        {
            throw new InvalidFileException("Unkown file detected.");
        }
        return parser;
    }

}



