package toys;

/**
 * The abstract superclass for all Toys
 *
 * @author Nick Salvemini
 */
public abstract class Toy implements IToy{

    /** The initial happiness of any toy */
    public final static int INITIAL_HAPPINESS = 0;

    /** The maximum happiness of any toy */
    public final static int MAX_HAPPINESS = 100;

    /** The initial wear of any toy */
    public final static double INITIAL_WEAR = 0.0;

    /** The product code for a toy */
    private final int productCode;

    /** The name of a toy */
    private final String name;

    /** The happiness of a toy */
    private int happiness;

    /** The wear on a toy */
    private double wear;

    /**
     * Constructor for Toys
     *
     * @param productCode Product code of the toy
     * @param name Name of the toy
     */
    protected Toy(int productCode, String name){
        this.productCode = productCode;
        this.name = name;
        happiness = INITIAL_HAPPINESS;
        wear = INITIAL_WEAR;
    }

    /** Gets the product code */
    @Override
    public int getProductCode(){
        return this.productCode;
    }

    /** Gets the name */
    @Override
    public String getName(){
        return this.name;
    }

    /** Gets the happiness */
    @Override
    public int getHappiness(){
        return this.happiness;
    }

    /** Tells whether the toy is retired */
    @Override
    public boolean isRetired(){
        return this.happiness >= MAX_HAPPINESS;
    }

    /** Gets the wear */
    @Override
    public double getWear(){
        return this.wear;
    }

    /**
     * Increases the wear
     *
     * @param amount Amount the wear increases
     */
    @Override
    public void increaseWear(double amount){
        this.wear += amount;
    }

    /**
     * Plays with the toy
     *
     * @param time Minutes to play for
     */
    @Override
    public void play(int time){
        System.out.println("PLAYING(" + time + "): " + this);
        specialPlay(time);
        this.happiness += time;
        if(isRetired()){
            System.out.println("RETIRED: " + this);
        }
    }

    /**
     * An abstract method for how the toy will
     * be played with, to be implemented in concrete
     * classes
     *
     * @param time Amount of minutes to play with the toy
     */
    abstract protected void specialPlay(int time);

    /**
     * Override of the toString function
     *
     * @return Returns a nicely formatted list
     *          of information about the toy
     */
    @Override
    public String toString(){
        return "Toy{PC:" + this.productCode +
                ", N:" + this.name +
                ", H:" + this.happiness +
                ", R:" + isRetired() +
                ", W:" + this.wear + "}";
    }
}
