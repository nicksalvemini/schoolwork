package toys;

/**
 * A concrete PlayDough that can be made
 * and played with
 *
 * @author Nick Salvemini
 */
public class PlayDough extends Toy{

    /** The product code of the PlayDough */
    private static int productCode = 100;

    /** The amount the wear increases per minute
     * of the PlayDough being played with */
    public final static double WEAR_MULTIPLIER = .05;

    /** The color of the PlayDough */
    private final Color color;

    /**
     * Constructor for the PlayDough toy
     *
     * @param name Name of the PlayDough
     * @param color Color of the PlayDough
     */
    protected PlayDough(String name, Color color){
        super(productCode, name);
        productCode++;
        this.color = color;
    }

    /**
     * Getter for the color
     *
     * @return Returns the color of the PlayDough
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Displays a play message for the PlayDough
     * and then increases its wear
     *
     * @param time Amount of minutes to play with the toy
     */
    @Override
    protected void specialPlay(int time){
        System.out.println("\tArts and crafting with " + this.color
        + " " + getName());
        increaseWear(time * WEAR_MULTIPLIER);
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the PlayDough
     */
    @Override
    public String toString(){
        return super.toString() + ", PlayDough{C:"
                + this.color + "}";
    }

}
