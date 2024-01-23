package test;

import rit.cs.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A test unit that exercises all the rit.cs Expression nodes.
 *
 * @author RIT CS
 */
public class TestComplexExpression {
    @Test
    public void testComplexExpression() {
        Expression root = new MulExpression(
                            new AddExpression(
                                    new SubExpression(
                                            new IntExpression(15),
                                            new IntExpression(3)
                                    ),
                                    new DivExpression(
                                            new IntExpression(20),
                                            new IntExpression(4)
                                    )
                            ),
                            new ModExpression(
                                    new IntExpression(36),
                                    new IntExpression(11)
                            )
        );

        assertEquals(51, root.evaluate());
        assertEquals("(((15 - 3) + (20 / 4)) * (36 % 11))", root.emit());
    }
}
