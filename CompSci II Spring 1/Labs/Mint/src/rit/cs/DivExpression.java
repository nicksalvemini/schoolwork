package rit.cs;

/**
 * A node representing division
 *
 * @author Nick Salvemini
 */
public class DivExpression implements Expression{

    /** The left child of the DivExpression */
    private Expression left;

    /** The right child of the DivExpression */
    private Expression right;

    /**
     * Creates a DivExpression node with
     * the left child and the right child
     *
     * @param left The left child Expression
     * @param right The right child Expression
     */
    public DivExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the left child divided by the
     * right child, or their resultant values
     * if they are not IntExpressions
     *
     * @return Returns left / right
     */
    public int evaluate(){
        return left.evaluate() / right.evaluate();
    }

    /**
     * Returns a String representing the expression
     * that a human would read
     *
     * @return A String that looks like ( left / right )
     */
    public String emit(){
        return "(" + left.emit() + " / " + right.emit() + ")";
    }
}
