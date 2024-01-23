package ritmath;

/**
 * Represents the single independent variable x
 *
 * @author Nick Salvemini
 */
public class Variable extends AbstractFunction{

    /** Constructor */
    Variable(){super();}

    /**
     * Tells whether the term is a constant
     *
     * @return Returns false because all instances
     *          of the Variable class are not constants
     */
    @Override
    public boolean isConstant() {
        return false;
    }

    /**
     * Evaluates the function at a given
     * value of x
     *
     * @param x The value of x
     * @return Returns the evaluation
     */
    @Override
    public double evaluate(double x) {
        return x;
    }

    /**
     * Finds dx/dx, which is always 1
     *
     * @return Returns 1
     */
    @Override
    public MathFunction derivative() {
        return FunctionFactory.constant(1);
    }

    /**
     * Evaluates the integral between two bounds
     *
     * @param lower Lower bound
     * @param upper Upper bound
     * @param accuracy Number of rectangles in the Riemann sum
     * @return Returns the result of the integral
     */
    public double integral(double lower, double upper, int accuracy){
        return ((upper * upper) / 2.0) - ((lower * lower) / 2.0);
    }

    /**
     * toString for the variable
     *
     * @return Returns x
     */
    @Override
    public String toString(){
        return "x";
    }
}
