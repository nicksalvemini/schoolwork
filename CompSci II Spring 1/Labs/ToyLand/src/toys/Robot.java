package toys;

/**
 * A concrete Robot that can be made
 * and played with
 *
 * @author Nick Salvemini
 */
public class Robot extends BatteryPowered{

    /** The product code of the RC Car */
    private static int productCode = 400;

    /** The fly speed of the Robot */
    public final static int FLY_SPEED = 25;

    /** The run speed of the Robot */
    public final static int RUN_SPEED = 10;

    /** The initial speed of the Robot */
    private final int INITIAL_SPEED = 0;

    /** Tells if the Robot is flying */
    private final boolean flying;

    /** The total distance the robot has travelled */
    private int distance;

    /**
     * Constructor for the Robot toy
     *
     * @param name Name of the Robot
     * @param numBatteries Number of batteries the Robot uses
     * @param flying Whether the Robot is flying or not
     */
    protected Robot(String name, int numBatteries, boolean flying){
        super(productCode, name, numBatteries);
        productCode++;
        this.flying = flying;
        distance = 0;
    }

    /**
     * Gets whether the Robot is flying
     *
     * @return Returns the Robot's flight status
     */
    public boolean isFlying(){
        return flying;
    }

    /**
     * Gets the total distance the robot has travelled
     *
     * @return Returns the distance
     */
    public int getDistance(){
        return distance;
    }

    /**
     * Displays a play message for the Robot
     * and then increases its wear
     *
     * @param time Amount of minutes to play with the Robot
     */
    @Override
    protected void specialPlay(int time){
        if(flying){
            distance += time * FLY_SPEED;
            System.out.println("\t" + getName() +
                    " is flying around with total distance: " + distance);
            increaseWear(FLY_SPEED);
        }else{
            distance += time * RUN_SPEED;
            System.out.println("\t" + getName() +
                    " is running around with total distance: " + distance);
            increaseWear(RUN_SPEED);
        }
        useBatteries(time);
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the Robot
     */
    @Override
    public String toString(){
        return super.toString() +
                ", Robot{F:" + flying +
                ", D:" + distance + "}";
    }
}
