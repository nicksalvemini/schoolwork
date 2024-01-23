package rit.cs;

/**
 * An interface for any Expression node
 *
 * @author Nick Salvemini
 */
public interface Expression {

    /** Returns an integer that is either the node itself or
     *  the result of the nodes expression */
    int evaluate();

    /** Returns a String representation of either the node itself
     *  or the expression the node represents */
    String emit();

}
