/***
 * NAME:    Output
 * PURPOSE: Used only to print things to the user.
 * AUTHOR:  Luke Healy
 * DATE:    3/10/16
 */

package simulation.view;

import java.text.DecimalFormat;
import java.util.*;
import simulation.model.Company;

public class Output
{
    private static final DecimalFormat formatter = new DecimalFormat("#0.00"); 
    private static final String formatLine = "+------+-----------------------+------------------+";


    /**
     * Prints the heading block.
     */
    public static void printHeading()
    {
        printFormatLine();
        System.out.println("| Year | Company               | Bank Balance     |");
    }

    /**
     * Prints a years worth of output.
     */
    public static void output(int year, List<Company> companies)
    {
        printFormatLine();
        for(Company c : companies)
        {
            double balance = c.getBankBalance();
            System.out.printf(
                "| %-5d| %-22s| $%-16s|\n", year, c.getName(), 
                (balance > 0.0 ? formatter.format(balance) : "(" + formatter.format(balance) + ")") );
        }
    }

    /**
     * Prints the sperarator line.
     */
    public static void printFormatLine()
    {
        System.out.println(formatLine);
    }
}