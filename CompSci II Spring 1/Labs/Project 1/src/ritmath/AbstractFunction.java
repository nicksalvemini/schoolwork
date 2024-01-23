package ritmath;

/**
 * A set of implementations for methods defined
 * in MathFunction
 *
 * @author Nick Salvemini
 */
public abstract class AbstractFunction implements MathFunction{

    /** Constructor */
    protected AbstractFunction(){}

    /**
     * Evaluates the integral between two bounds
     * using trapezoidal estimation
     *
     * @param lower Lower bound
     * @param upper Upper bound
     * @param accuracy Number of trapezoids to use
     * @return Returns the result of the integral
     */
    @Override
    public double integral(double lower, double upper, int accuracy){
        double dx = (upper - lower) / accuracy;
        double result = 0;
        double currentX = lower;

        for(double i = 0; i < accuracy; i += 1){
            result += (dx / 2.0) * (this.evaluate(currentX + dx) + this.evaluate(currentX));
            currentX += dx;
        }

        return result;
    }

    /**
     * Abstract declaration of the isConstant method;
     * each function will Override this
     *
     * @return Returns whether the function is constant
     *          or not
     */
    @Override
    public abstract boolean isConstant();

    /** Forces all children to override toString */
    @Override
    public abstract String toString();
}
