package mastermind;
import java.util.Scanner;

/**
 * The main class for the Mastermind game.  The secret code can be provided
 * as the command line argument, otherwise it will be randomly generated.
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class MasterMind {
    /** the number of digits in the secret code */
    public final static int SECRET_CODE_LENGTH = 4;
    /** the minimum digit in the code */
    public final static int MIN_CODE_DIGIT = 1;
    /** the maximum digit in the code */
    public final static int MAX_CODE_DIGIT = 8;
    /** the number of turns in the game */
    private final static int MAX_TURNS = 10;
    /** The code maker */
    private static CodeMaker codeMaker;
    /** The code breaker */
    private static CodeBreaker codeBreaker;
    /** The list of guesses */
    private static Guesses guesses;
    /** A value responsible for breaking the loop if quit is called */
    private static boolean quit = false;
    /** A value responsible for checking if the Code Breaker has won */
    private static boolean win = false;

    /**
     * Creates a MasterMind object and initializes
     * a codeMaker, codeBreaker, and guess list through
     * their respective classes
     *
     * @param secretCode Code to be guessed and
     *                   stored in the Code maker
     */
    public MasterMind(String secretCode){
        codeMaker = new CodeMaker(secretCode);
        codeBreaker = new CodeBreaker();
        guesses = new Guesses();
    }

    /**
     * Check that the digits in a code are all in the valid range.
     *
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @return whether the code is valid or not
     */
    public static boolean codeInRange(String code) {
        for (int i = 0; i < code.length(); ++i) {
            if (Character.getNumericValue(code.charAt(i)) < MIN_CODE_DIGIT ||
                    Character.getNumericValue(code.charAt(i)) > MAX_CODE_DIGIT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check that there are no duplicates in the code.
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @rit.pre code has all digits in valid range
     * @return whether the code has no duplicates (true) or does have duplicates (false)
     */
    public static boolean codeNoDuplicates(String code) {
        return code.length() == code.chars().distinct().count();
    }

    /**
     * Accepts user input as the secret code to play the game
     */
    public static void getCode(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Secret Code to begin game > ");
        new MasterMind(in.nextLine());
        System.out.println("----------------\n");
    }

    /**
     * Prints a simple welcome message
     */
    public static void displayWelcome(){
        System.out.println("Welcome to Mastermind!\nEnter your 4 digit guesses (q to quit)");
    }

    /**
     * Calculates the score of the codeBreaker for the
     * whole game
     *
     * @param turns Number of turns it took
     *              for the codeBreaker to win/lose,
     *              used to calculate the average score
     * @return Returns the average score of the codeBreaker
     */
    public static double calcScore(int turns){
        return (double)codeBreaker.getScore() / turns;
    }

    /**
     * Displays an end message that tells whether
     * the codeBreaker quit, won, or lost and what
     * the final score was
     *
     * @param score The final score of the codeBreaker
     */
    public static void displayEnd(double score){
        if(quit){System.out.println("Program quit successfully.");}
        else {
            if (win) {
                System.out.println("You won!\nCode breaker score: " + score);
            } else {
                System.out.println("You lost! The secret code was: " + codeMaker.getSecretCode()
                        + "\nCode breaker score: " + score);
            }
        }
    }

    /**
     * Accepts a guess from the codeBreaker
     * and evaluates it. If valid, it adds it
     * to the guess list, changes the score,
     * and checks the correctness of the guess before
     * displaying the guess list again
     */
    public static void acceptGuess() {
        Guess guess = codeBreaker.getGuess();
        if(guess == null){quit = true;}
        else if (!guess.getString().equals("INVALID")){
            guesses.addGuess(guess);
            codeMaker.checkGuess(guess);
            codeBreaker.increaseScore(2 * (guess.getCorrectPositions()) + guess.getWrongPositions());
            guesses.displayGuesses();
            if(guess.getCorrectPositions() == 4){win = true;}
        }
    }

    /**
     * Displays messages accordingly and
     * plays the game
     */
    public static void playGame(){
        displayWelcome();
        int turn = 0;
        do {
            acceptGuess();
            turn++;
            if(win){break;}
        }while(!quit && turn < MAX_TURNS);
        displayEnd(calcScore(turn));
    }

    /**
     * The main method verifies the command line code (if present) and
     * then plays the game.
     *
     * If there is no command line code, the main method accepts
     * user input as the secret code, verifies it, and then plays the game
     *
     * @param args command line arguments (the secret number, optionally)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            getCode();
            String code = codeMaker.getSecretCode();
            if (code.length() != SECRET_CODE_LENGTH) {
                System.out.println("Secret number " + code +
                        " is not length " + SECRET_CODE_LENGTH);
                return;
            } else if (!codeInRange(code)) {
                System.out.println("Secret number " + code +
                        " is invalid.  Expect all digits to be between " +
                        MIN_CODE_DIGIT + " and " +
                        MAX_CODE_DIGIT);
                return;
            } else if (!codeNoDuplicates(code)) {
                System.out.println("Secret number " + code +
                        " is invalid.  Has duplicate digits.");
                return;
            }
        } else {
            if (args[0].length() != SECRET_CODE_LENGTH) {
                System.out.println("Secret number " + args[0] +
                        " is not length " + SECRET_CODE_LENGTH);
                return;
            } else if (!codeInRange(args[0])) {
                System.out.println("Secret number " + args[0] +
                        " is invalid.  Expect all digits to be between " +
                        MIN_CODE_DIGIT + " and " +
                        MAX_CODE_DIGIT);
                return;
            } else if (!codeNoDuplicates(args[0])) {
                System.out.println("Secret number " + args[0] +
                        " is invalid.  Has duplicate digits.");
                return;
            }
            new MasterMind(args[0]);
        }
        playGame();
    }
}