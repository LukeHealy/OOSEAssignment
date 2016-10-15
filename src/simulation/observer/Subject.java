/***
 * NAME:    Subject
 * PURPOSE: Basic subject interface to notify observers of a change in wages.
 * AUTHOR:  Luke Healy
 * DATE:    4/10
 */

package simulation.observer;

public interface Subject
{
    // Attaches an observer.
    public void attach(Observer observer);
    // Notify everyone of a change.
    public void notifyObservers(double wage);
}