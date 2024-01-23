package toys;

/**
 * A concrete RC Car that can be made
 * and played with
 *
 * @author Nick Salvemini
 */
public class RCCar extends BatteryPowered{

    /** The product code of the RC Car */
    private static int productCode = 300;

    /** The starting speed */
    public final static int STARTING_SPEED = 10;

    /** The speed increase */
    public final static int SPEED_INCREASE = 5;

    /** The speed of the RC Car */
    private int speed;

    /**
     * Constructor for the RC Car toy
     *
     * @param name Name of the RC Car
     * @param numBatteries Number of batteries the RC Car uses
     */
    protected RCCar(String name, int numBatteries){
        super(productCode, name, numBatteries);
        productCode++;
        speed = STARTING_SPEED;
    }

    /**
     * Get the speed of the RC Car
     *
     * @return Returns the speed
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Displays a play message for the RC Car
     * and then increases its wear
     *
     * @param time Amount of minutes to play with the RC Car
     */
    @Override
    protected void specialPlay(int time){
        System.out.println("\t" + getName() +
                " races around at " + speed + "mph!");
        useBatteries(time);
        increaseWear(speed);
        speed += SPEED_INCREASE;
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the RC Car
     */
    @Override
    public String toString(){
        return super.toString() +
                ", RCCar{S:" + speed + "}";
    }
}
