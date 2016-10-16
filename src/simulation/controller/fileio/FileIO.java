/***
 * NAME:    FileIO
 * PURPOSE: Contains file reading functionality and the Parser factory.
 * AUTHOR:  Luke Healy
 * DATE:    30/9/16
 */
package simulation.controller.fileio;

import simulation.model.FileData;
import simulation.controller.exceptions.*;

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
     * Read any csv file and return its contents as an arraylist of lines.
     */
    private List<String> readCSVFile(String path) throws FileNotFoundException, CouldNotLoadDataException
    {
        String line;
        List<String> file = new ArrayList<String>();
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
        // Some issue with the file io so close the file.
        catch(IOException e2)
        {
            try
            {
                if(fis != null)
                {
                    fis.close();
                }
            }
            // Can't close the file.
            catch(IOException e3)
            {
                System.out.println("Can't close file.");
                throw new CouldNotLoadDataException(e3.getMessage());
            }
            throw new CouldNotLoadDataException(e2.getMessage());
        }
        finally
        {
            try
            {
                if(fis != null)
                {
                    fis.close();
                }
            }
            // Can't close the file.
            catch(IOException e3)
            {
                System.out.println("Can't close file.");
                throw new CouldNotLoadDataException(e3.getMessage());
            }
        }
        return file;
    }

    /**
     * makeParser is the Parser factory used by readFile.
     * It looks at the file header and returns the appropriate file parser.
     * If the header is not recognizable, throw an exception.
     */
    public Parser makeParser(String fileType) throws CouldNotLoadDataException
    {
        Parser parser = null;

        if(fileType.equals("Name,Type,Owner,Worth,Revenue,Wages"))
        {
            parser = new PropertyFileParser();
        }
        else if(fileType.equals("Year,Event,Property"))
        {
            parser = new EventFileParser();
        }
        else if(fileType.equals("Year,Buy/Sell,Property"))
        {
            parser = new PlanFileParser();
        }
        else
        {
            throw new CouldNotLoadDataException("Unknown file detected.");
        }
        return parser;
    }

    /**
     * Takes an array of the file names and reads each file. 
     * It then gets the appropriate parser and passes the read csv to it.
     * Also strips the header from the file as we no longer need it.
     * The file io calls all happen three times, once for each file.
     * The order of the files does not matter.
     */ 
    public void readFiles(String[] args) throws CouldNotLoadDataException
    {
        try
        {

            List<String> file;
            Parser parser;

            for(int i = 0; i < 3; i++)
            {
                // Read file.
                file = readCSVFile(args[i]);
                // Get the parser according to the header.
                parser = makeParser(file.get(0));
                // We can get rid of the header now.
                file.remove(0);
                // Parse the file.
                parser.parseFile(file, fileData);
            }
        }
        catch(FileNotFoundException | InvalidFileException e)
        {
            throw new CouldNotLoadDataException(e.getMessage());
        }
    }

}



