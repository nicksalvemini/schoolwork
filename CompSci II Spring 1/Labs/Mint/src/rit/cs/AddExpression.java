package rit.cs;

/**
 * A node representing addition
 *
 * @author Nick Salvemini
 */
public class AddExpression implements Expression{

    /** The left child of the AddExpression */
    private Expression left;

    /** The right child of the AddExpression */
    private Expression right;

    /**
     * Creates an AddExpression node with
     * the left child and the right child
     *
     * @param left The left child Expression
     * @param right The right child Expression
     */
    public AddExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the value of the left child plus
     * the value of the right child
     *
     * @return Returns left + right
     */
    public int evaluate(){
        return left.evaluate() + right.evaluate();
    }

    /**
     * Returns a String representing the expression
     * that a human would read
     *
     * @return A String that looks like ( left + right )
     */
    public String emit(){
        return "(" + left.emit() + " + " + right.emit() + ")";
    }
}
