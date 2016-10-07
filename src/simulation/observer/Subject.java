/***
 * NAME:    
 * PURPOSE: 
 * AUTHOR:  Luke Healy
 * DATE:    
 */

package simulation.observer;

public interface Subject
{
    public void attach(Observer observer);
    public void notifyObservers(double wage);
}