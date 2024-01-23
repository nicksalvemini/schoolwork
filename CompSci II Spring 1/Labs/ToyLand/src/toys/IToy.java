package toys;

/**
 * An Interface for all Toys
 *
 * @author Nick Salvemini
 */
public interface IToy {

    /** Gets the product code */
    int getProductCode();

    /** Gets the name */
    String getName();

    /** Gets the happiness */
    int getHappiness();

    /** Tells whether the toy is retired */
    boolean isRetired();

    /** Gets the wear */
    double getWear();

    /**
     * Increases the wear
     *
     * @param amount Amount the wear increases
     */
    void increaseWear(double amount);

    /**
     * Plays with the toy
     *
     * @param time Minutes to play for
     */
    void play(int time);

}
