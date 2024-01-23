package ritmath;

/**
 * Represents a constant
 *
 * @author Nick Salvemini
 */
public class Constant extends AbstractFunction {

    /**
     * The value of the constant
     */
    private final double value;

    /**
     * Constructor
     *
     * @param value The value of the constant
     */
    Constant(double value) {
        super();
        this.value = value;
    }

    /**
     * Tells whether the term is a constant
     *
     * @return Returns true because all instances
     * of the Constant class are constants
     */
    @Override
    public boolean isConstant() {
        return true;
    }

    /**
     * Evaluates the value of the constant
     *
     * @param x The value of x
     * @return Returns the value of the constant
     * regardless of the value of x
     */
    @Override
    public double evaluate(double x) {
        return value;
    }

    /**
     * Returns the derivative of the term
     *
     * @return Returns a new constant 0 since
     * the derivative of a constant is
     * always 0
     */
    @Override
    public MathFunction derivative() {
        return FunctionFactory.constant(0);
    }

    /**
     * Evaluates the integral between two bounds
     *
     * @param lower Lower bound
     * @param upper Upper bound
     * @param accuracy Number of rectangles in the Riemann sum
     * @return Returns the result of the integral
     */
    @Override
    public double integral(double lower, double upper, int accuracy) {
        return value * (upper - lower);
    }

    /**
     * toString of a constant
     *
     * @return Returns the constant neatly
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }
}
