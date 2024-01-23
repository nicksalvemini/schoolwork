package mastermind;

/**
 * Represents a single guess and
 * all the associated pieces of information
 *
 * @author Nick Salvemini
 */
public class Guess {
    /** Assigns a guess number to each new guess
     * and keeps track of how many valid guesses
     * have been created */
    private static int currentGuessNumber = 1;
    /** Represents which number guess the current instance is */
    private int guessNumber;
    /** Guess in string form, 4-digit code */
    private final String guess;
    /** Number of correctly positioned digits */
    private int correctPositions;
    /** Number of incorrectly positioned digits */
    private int wrongPositions;

    /**
     * Alternate constructor used for invalid
     * guesses so that the current guess number
     * does not increase for an invalid guess,
     * because the CodeBreaker.getGuess() must
     * return a guess
     */
    public Guess(){ guess = "INVALID"; }

    /**
     * Constructor used for valid guesses that
     * will be played and displayed
     *
     * @param guess String representing a guess
     *              to be turned into a Guess object
     */
    public Guess(String guess) {
        this.guess = guess;
        guessNumber = currentGuessNumber;
        currentGuessNumber++;
    }

    /**
     * Accesses the private correctPositions field
     *
     * @return Returns the number of correctly
     *          positioned digits in this guess
     */
    public int getCorrectPositions() {
        return correctPositions;
    }

    /**
     * Sets the private correctPositions field
     *
     * @param correctPositions The value to set the field to
     */
    public void setCorrectPositions(int correctPositions) {
        this.correctPositions = correctPositions;
    }

    /**
     * Accesses the private wrongPositions field
     *
     * @return Returns the number of incorrectly
     *          positioned digits in this guess
     */
    public int getWrongPositions() {
        return wrongPositions;
    }

    /**
     * Sets the private wrongPositions field
     *
     * @param wrongPositions The value to set the field to
     */
    public void setWrongPositions(int wrongPositions){
        this.wrongPositions = wrongPositions;
    }

    /**
     * Accesses the String format of the guess
     *
     * @return Returns the guess String
     */
    public String getString() {
        return guess;
    }

    /**
     * Overrides the toString() for the guess to provide
     * easily understood information
     *
     * @return Returns neatly displayed information about
     * the guess
     */
    @Override
    public String toString() {
        return "Guess #" + this.guessNumber + ": " + guess + " (B:"
                + correctPositions + " W:" + wrongPositions + ")";
    }
}
