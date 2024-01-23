package ritmath;

/**
 * Set of operations that all functions
 * in this system must support
 *
 * @author Nick Salvemini
 */
public interface MathFunction {

    /** Returns a new function that is the derivative
     *  of the first function
     *
     * @return Returns f'(x)
     */
    MathFunction derivative();

    /**
     * Computes the value of a function
     *
     * @param x The function
     * @return Returns the evaluation
     */
    double evaluate(double x);

    /**
     * Tells whether a function will evaluate to the
     * same value, regardless of x
     *
     * @return Returns false
     */
    boolean isConstant();

    /**
     * Evaluates the integral between two bounds
     *
     * @param lower Lower bound
     * @param upper Upper bound
     * @param accuracy The number of rectangles for the
     *                 Riemann sum
     * @return Returns the result of the integral
     */
    double integral(double lower, double upper, int accuracy);
}
