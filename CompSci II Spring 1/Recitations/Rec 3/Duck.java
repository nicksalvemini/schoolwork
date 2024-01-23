/**
 * Duck
 *
 * @author Nick Salvemini
 */
public class Duck implements Animal, Swimmer, Flyer {

    public final static double FLY_SPEED_MS = 22.352;

    public final static double RUN_SPEED_MS = 13.94765;

    public final static double SWIM_SPEED_MS = 26.8224;

    private double wingSpan;

    private int happiness;

    public Duck(double wingSpan){
        this.wingSpan = wingSpan;
        happiness = 0;
    }

    public void pet(int seconds){
        happiness += seconds * wingSpan;
    }

    public int getHappiness(){
        return happiness;
    }

    @Override
    public double run(int seconds){
        return seconds * RUN_SPEED_MS + wingSpan;
    }

    @Override
    public String speak(){
        return "quack!, quack!";
    }

    @Override
    public double dive(int minutes){
        return minutes * SWIM_SPEED_MS;
    }

    @Override
    public double fly(int seconds){
        return seconds * FLY_SPEED_MS * wingSpan;
    }

    @Override
    public String toString(){
        return "Duck{Wingspan = " + wingSpan +
                ", Happiness = " + happiness +
                "}";
    }

}
