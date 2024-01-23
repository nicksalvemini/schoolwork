package test;

import mastermind.Guess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test framework for the Guess class.
 *
 * @author RIT CS
 */
public class TestGuess {
    /** Test all aspects of the Guess class */
    @Test
    public void testGuess() {
        Guess guess1 = new Guess("1234");
        assertEquals("Guess #1: 1234 (B:0 W:0)", guess1.toString());

        guess1.setCorrectPositions(1);
        assertEquals(1, guess1.getCorrectPositions());
        assertEquals("Guess #1: 1234 (B:1 W:0)", guess1.toString());

        guess1.setWrongPositions(2);
        assertEquals(2, guess1.getWrongPositions());
        assertEquals("Guess #1: 1234 (B:1 W:2)", guess1.toString());

        Guess guess2 = new Guess("5612");
        assertEquals("Guess #2: 5612 (B:0 W:0)", guess2.toString());

        guess2.setCorrectPositions(3);
        assertEquals(3, guess2.getCorrectPositions());
        assertEquals("Guess #2: 5612 (B:3 W:0)", guess2.toString());

        guess2.setWrongPositions(1);
        assertEquals(1, guess2.getWrongPositions());
        assertEquals("Guess #2: 5612 (B:3 W:1)", guess2.toString());
    }
}
