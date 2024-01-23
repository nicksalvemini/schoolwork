package ritmath;

/**
 * Represents the cosine function
 *
 * @author Nick Salvemini
 */
public class Cosine extends AbstractFunction{

    /** The argument of the cosine function */
    private final MathFunction arg;

    /** Constructor
     *
     * @param arg Argument of the cosine function
     */
    Cosine(MathFunction arg){
        super();
        this.arg = arg;
    }

    /**
     * Tells whether the cosine function
     * is a constant value
     *
     * @return Returns true iff the argument
     *          of the cosine function is constant
     */
    @Override
    public boolean isConstant(){
        return arg.isConstant();
    }

    /**
     * Evaluates the cosine for any given
     * value of x
     *
     * @param x The value of x
     * @return Returns the evaluation of the
     *          cosine function
     */
    @Override
    public double evaluate(double x){
        return Math.cos(arg.evaluate(x));
    }

    /**
     * Takes the derivative of the cosine using
     * the rule d(cos(u))/dx = -sin(u) * du/dx
     *
     * @return Returns the new Product instance
     *          (Or Sine, if the argument is just x)
     */
    @Override
    public MathFunction derivative(){
        if(arg == FunctionFactory.x()){
            return FunctionFactory.product(FunctionFactory.constant(-1),
                    FunctionFactory.sine(arg));
        }else{
            return FunctionFactory.product(FunctionFactory.constant(-1),
                                            FunctionFactory.sine(arg),
                                            arg.derivative());
        }
    }

    /**
     * toString of a Cosine
     *
     * @return Returns "cos( arg )"
     */
    @Override
    public String toString(){
        return "cos( " + arg + " )";
    }
}
