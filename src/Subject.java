public interface Subject
{
    public void attach(Observer observer);
    public void notifyObservers();
    public Object getState();
}