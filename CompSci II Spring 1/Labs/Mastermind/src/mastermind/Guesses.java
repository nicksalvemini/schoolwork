package mastermind;
import java.util.ArrayList;

/**
 * Stores all valid guesses
 *
 * @author Nick Salvemini
 */
public class Guesses {
    /** ArrayList of all the valid guesses */
    private final ArrayList<Guess> guessList;

    /**
     * Creates a new ArrayList to store the
     * guesses
     */
    public Guesses(){
        guessList = new ArrayList<>();
    }

    /**
     * Adds a guess to the list of guesses
     *
     * @param guess Guess to be added to the
     *              list
     */
    public void addGuess(Guess guess){
        guessList.add(guess);
    }

    /**
     * Displays all the guesses neatly
     */
    public void displayGuesses(){
        for(Guess guess : guessList){
            System.out.println(guess);
        }
    }
}
