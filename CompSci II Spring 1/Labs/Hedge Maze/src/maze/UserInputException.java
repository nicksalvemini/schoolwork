package maze;

/**
 * Creating this new exception makes it easier to provide meaningful feedback
 * to the user when they are typing things in the console.
 */
public class UserInputException extends Exception {
    /**
     * Create an instance of this exception.
     * @param msg an explanation that will be informative to the user
     */
    public UserInputException( String msg ) {
        super( msg );
    }
}
