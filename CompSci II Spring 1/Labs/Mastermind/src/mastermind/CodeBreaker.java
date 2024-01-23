package mastermind;
import java.util.Scanner;

/**
 * Responsible for accepting user guesses and keeping
 * track of the user score
 *
 * @author Nick Salvemini
 */
public class CodeBreaker {

    /** Character responsible for quitting the program if entered */
    private final static char QUIT = 'q';
    /** Score of user that accumulates with each guess */
    private int score = 0;

    /**
     * Responsible for taking user input as a guess
     *
     * @return Returns a validly formatted guess, or null
     *          if the quit character is entered
     */
    public Guess getGuess(){
        Scanner in = new Scanner(System.in);

        System.out.print("? ");
        String guess = in.nextLine();
        if(isValid(guess)){
            if(guess.charAt(0) == QUIT){ return null; }
            else{return new Guess(guess);}
        }
        else{return new Guess();}
    }

    /**
     * Checks the validity of an input as a helper function for
     * getGuess(). Prints error messages for invalid input
     *
     *
     * @param guess String representing the user's guess input
     * @return Returns true if the guess is validly formatted
     *          or false otherwise
     */
    private boolean isValid(String guess){
        if(guess.length() == 1){
            if(guess.charAt(0) == QUIT){ return true; }
            else{System.out.println("Guess must have 4 digits!"); return false;}
        }
        else {
            if (guess.length() == 4) {
                if (MasterMind.codeInRange(guess)) {
                    if (MasterMind.codeNoDuplicates(guess)) {
                        return true;
                    } else {System.out.println("Guess cannot have duplicate digits!"); return false;}
                } else {System.out.println("Guess must have all digits between 1 and 8!"); return false;}
            } else {System.out.println("Guess must have 4 digits!"); return false;}
        }
    }

    /**
     * Increases the score of the codebreaker by a
     * given amount
     *
     * @param amount Determines how much to increase the
     *               score by
     */
    public void increaseScore(int amount){
        score += amount;
    }

    /**
     * Accesses the instance variable score
     *
     * @return Returns the score
     */
    public int getScore(){
        return score;
    }
}
