package rit.cs;

/**
 * A node representing the modulus
 * operator
 *
 * @author Nick Salvemini
 */
public class ModExpression implements Expression{

    /** The left child of the ModExpression */
    private Expression left;

    /** The right child of the ModExpression */
    private Expression right;

    /**
     * Creates a ModExpression node with
     * the left child and the right child
     *
     * @param left The left child Expression
     * @param right The right child Expression
     */
    public ModExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the left value mod the right
     * value, or their resultant values
     * if they are not IntExpressions
     *
     * @return Returns left % right
     */
    public int evaluate(){
        return left.evaluate() % right.evaluate();
    }

    /**
     * Returns a String representing the expression
     * that a human would read
     *
     * @return A String that looks like ( left % right )
     */
    public String emit(){
        return "(" + left.emit() + " % " + right.emit() + ")";
    }
}
