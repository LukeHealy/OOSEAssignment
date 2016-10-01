public class test
{
    public static void main(String[] args) {
        Simulation s = new Simulation();
        System.out.println(s);
        System.out.println(Simulation.getSimulationInstance());
        Simulation s2 = new Simulation();
        System.out.println(s2);
        System.out.println(Simulation.getSimulationInstance());
    }
}