package simulation.view;

import java.text.DecimalFormat;
import java.util.*;

import simulation.model.Company;

public class Output
{
    private static final DecimalFormat formatter = new DecimalFormat("#0.00"); 
    public static final String formatLine = "+------+-----------------------+------------------+";

    public static void printHeading()
    {
        System.out.println(formatLine);
        System.out.println("| Year | Company               | Bank Balance     |");
    }

    public static void output(int year, ArrayList<Company> companies)
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
}