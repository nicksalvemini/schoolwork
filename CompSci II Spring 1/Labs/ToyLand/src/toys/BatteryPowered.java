package toys;

/**
 * The abstract superclass for all battery
 * powered toys
 *
 * @author Nick Salvemini
 */
public abstract class BatteryPowered extends Toy{

    /** The maximum battery percentage */
    public final static int FULLY_CHARGED = 100;

    /** The minimum battery percentage */
    public final static int DEPLETED = 0;

    /** The current battery level of the toy */
    private int batteryLevel;

    /** The number of batteries the toy uses */
    private final int numBatteries;

    /**
     * Constructor for battery powered toys
     *
     * @param productCode Product code of the toy
     * @param name Name of the toy
     * @param numBatteries The number of batteries the toy uses
     */
    public BatteryPowered(int productCode, String name, int numBatteries){
        super(productCode, name);
        batteryLevel = FULLY_CHARGED;
        this.numBatteries = numBatteries;
    }

    /**
     * Gets the battery level of the toy
     *
     * @return Returns the battery level
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }

    /**
     * Gets the number of batteries the toy uses
     *
     * @return Returns the number of batteries
     */
    public int getNumBatteries(){
        return numBatteries;
    }

    /**
     * Uses the batteries on the toy
     *
     * @param time The time the batteries are used
     */
    public void useBatteries(int time){
        batteryLevel -= numBatteries + time;
        if(batteryLevel <= DEPLETED){
            batteryLevel = DEPLETED;
            System.out.println("\tDEPLETED:" + this);
            batteryLevel = FULLY_CHARGED;
            System.out.println("\tRECHARGED:" + this);
        }
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the Battery Powered toy
     */
    @Override
    public String toString(){
        return super.toString() +
                ", BatteryPowered{BL:" + batteryLevel +
                ", NB:" + numBatteries + "}";
    }
}
