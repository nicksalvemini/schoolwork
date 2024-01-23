package test;

import mastermind.Guess;
import mastermind.Guesses;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test framework for the Guesses class.
 *
 * @author RIT CS
 */
public class TestGuesses {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /** Test Guesses can store and display a sequence of Guess objects. */
    @Test
    public void testGuesses() {
        Guesses guesses = new Guesses();
        guesses.addGuess(new Guess("1234"));
        guesses.addGuess(new Guess("5612"));
        guesses.addGuess(new Guess("3641"));

        String expected = "Guess #1: 1234 (B:0 W:0)" + System.lineSeparator();
        expected += "Guess #2: 5612 (B:0 W:0)" + System.lineSeparator();
        expected += "Guess #3: 3641 (B:0 W:0)";
        guesses.displayGuesses();

        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }
}
