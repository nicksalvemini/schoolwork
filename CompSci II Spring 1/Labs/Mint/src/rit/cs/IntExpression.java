package rit.cs;

/**
 * A node representing an integer
 *
 * @author Nick Salvemini
 */
public class IntExpression implements Expression{

    /** The value of the integer */
    private int value;

    /**
     * Creates an IntExpression with the value
     * of the integer
     *
     * @param value The value of the integer
     */
    public IntExpression(int value){
        this.value = value;
    }

    /**
     * Returns the value of the integer
     *
     * @return The value of the integer
     */
    public int evaluate(){
        return value;
    }

    /**
     * Returns the integer in String form
     *
     * @return Returns the value as a String
     */
    public String emit(){
        return Integer.toString(value);
    }
}
