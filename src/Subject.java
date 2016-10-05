public interface Subject
{
    public void attach(Observer observer);
    public void notifyObservers(double wage);
}