package ritmath;

/**
 * Represents the sine function
 *
 * @author Nick Salvemini
 */
public class Sine extends AbstractFunction{

    /** The argument of the sine function */
    private final MathFunction arg;

    /** Constructor
     *
     * @param arg Argument of the sine function
     */
    Sine(MathFunction arg){
        super();
        this.arg = arg;
    }

    /**
     * Tells whether the sine function
     * is a constant value
     *
     * @return Returns true iff the argument
     *          of the sine function is constant
     */
    @Override
    public boolean isConstant(){
        return arg.isConstant();
    }

    /**
     * Evaluates the sine for any given
     * value of x
     *
     * @param x The value of x
     * @return Returns the evaluation of the sine
     *          function
     */
    @Override
    public double evaluate(double x){
        return Math.sin(arg.evaluate(x));
    }

    /**
     * Takes the derivative of the sine using
     * the rule d(sin(u))/dx = cos(u) * du/dx
     *
     * @return Returns the new Product instance
     *          (Or Cosine, if the argument is just x)
     */
    @Override
    public MathFunction derivative(){
        if(arg == FunctionFactory.x()){return FunctionFactory.cosine(arg);}
        else{return FunctionFactory.product(FunctionFactory.cosine(arg), arg.derivative());}
    }

    /**
     * toString of a Sine
     *
     * @return Returns "sin( arg )"
     */
    @Override
    public String toString(){
        return "sin( " + arg + " )";
    }
}
