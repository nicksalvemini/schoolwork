package mastermind;

/**
 * Responsible for making/storing the secret code, as
 * well as comparing the code to guesses received from
 * the Codebreaker
 *
 * @author Nick Salvemini
 */
public class CodeMaker {
    /** Secret code represented as a string of 4 digits */
    private final String secretCode;

    /**
     * Creates an instance of the CodeMaker class
     * with a secret code
     *
     * @param code The secret code
     */
    public CodeMaker(String code){
        secretCode = code;
    }

    /**
     * Compares the guess to the secret code
     * and counts how many correct/wrong positioned
     * digits are in the guess using helper functions
     *
     * @pre The guess is validly formatted
     * @param guess The guess being checked
     */
    public void checkGuess(Guess guess){
        guess.setCorrectPositions(countCorrectPositions(guess));
        guess.setWrongPositions(countWrongPositions(guess));
    }

    /**
     * Helper function for checkGuess() to count
     * the number of correctly positioned digits
     * in the guess
     *
     * @param guess The guess being compared to the code
     * @return Returns an int representing the number of
     *          correctly positioned digits
     */
    private int countCorrectPositions(Guess guess){
        int correct = 0;
        for(int i = 0; i < 4; i++){
            if(secretCode.charAt(i) == (guess.getString().charAt(i))){correct++;}
        }
        return correct;
    }

    /**
     * Helper function for checkGuess() to count
     * the number of incorrectly positioned digits
     * in the guess
     *
     * @param guess The guess being compared to the code
     * @return Returns an int representing the number of
     *          incorrectly positioned digits
     */
    private int countWrongPositions(Guess guess){
        int wrong = 0;
        for(int i = 0; i < 4; i++){
            if(secretCode.indexOf(guess.getString().charAt(i)) != -1){wrong++;}
        }
        wrong -= guess.getCorrectPositions();
        return wrong;
    }

    /**
     * Accesses and returns the secret code
     * as a String
     *
     * @return Returns the secret code
     */
    public String getSecretCode(){
        return secretCode;
    }
}
