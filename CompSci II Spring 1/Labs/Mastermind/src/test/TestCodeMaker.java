package test;

import mastermind.CodeMaker;
import mastermind.Guess;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test framework for the CodeMaker class.
 *
 * @author RIT CS
 */
public class TestCodeMaker {
    /** Test secret code making. */
    @Test
    @Order(1)
    public void testSecretCode() {
        CodeMaker codeMaker = new CodeMaker("2163");
        assertEquals("2163", codeMaker.getSecretCode());

        codeMaker = new CodeMaker("6451");
        assertEquals("6451", codeMaker.getSecretCode());
    }

    /** Test making guesses. */
    @Test
    @Order(2)
    public void testCheckGuess() {
        CodeMaker codeMaker = new CodeMaker("1436");

        Guess guess = new Guess("1625");
        codeMaker.checkGuess(guess);
        assertEquals(1, guess.getCorrectPositions());
        assertEquals(1, guess.getWrongPositions());

        guess = new Guess("3478");
        codeMaker.checkGuess(guess);
        assertEquals(1, guess.getCorrectPositions());
        assertEquals(1, guess.getWrongPositions());

        guess = new Guess("1436");
        codeMaker.checkGuess(guess);
        assertEquals(4, guess.getCorrectPositions());
        assertEquals(0, guess.getWrongPositions());

        guess = new Guess("6143");
        codeMaker.checkGuess(guess);
        assertEquals(0, guess.getCorrectPositions());
        assertEquals(4, guess.getWrongPositions());
    }
}
