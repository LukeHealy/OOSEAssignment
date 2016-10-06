package simulation.events;
import simulation.controller.exceptions.InvalidEventException;
import simulation.controller.Simulation;


public interface Eventful
{
    public void doEvent(Simulation sim) throws InvalidEventException;
}