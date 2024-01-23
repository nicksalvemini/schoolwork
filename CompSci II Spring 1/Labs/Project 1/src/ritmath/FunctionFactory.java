package ritmath;

/**
 * Contains the static methods that client
 * programs call to create function structures
 *
 * @author Nick Salvemini
 */
public class FunctionFactory {

    /** The singleton variable */
    static Variable x = new Variable();

    /** Constructor */
    public FunctionFactory(){}

    /**
     * Creates a new constant term with the
     * provided value
     *
     * @param constant Value of the constant
     * @return Returns the term as a MathFunction
     */
    public static MathFunction constant(double constant){
        return new Constant(constant);
    }

    /**
     * Accesses the singleton variable x
     *
     * @return Returns the variable as a MathFunction
     */
    public static MathFunction x(){
        return x;
    }

    /**
     * Creates a sum of MathFunction terms
     *
     * @param terms The native array of terms to be
     *          summed up, all are MathFunctions
     * @return Returns the Sum of all the terms
     */
    public static MathFunction sum(MathFunction... terms){
        MathFunction sum = new Sum(terms);
        if(sum.isConstant()) {
            return constant(sum.evaluate(0));
        }else{
            return sum;
        }
    }

    /**
     * Creates a product of MathFunction terms
     *
     * @param terms The factors to be multiplied
     */
    public static MathFunction product(MathFunction... terms){
        MathFunction product = new Product(terms);
        if(product.isConstant()){
            return constant(product.evaluate(0));
        }else if(product.evaluate(1) == 0){
            return constant(0);
        }else{
            return product;
        }
    }

    /**
     * Creates a sine instance of the argument
     *
     * @param arg Argument of the sine function
     */
    public static MathFunction sine(MathFunction arg){
        MathFunction sine = new Sine(arg);
        if(sine.isConstant()){return constant(sine.evaluate(0));}
        else{return sine;}
    }

    /**
     * Creates a cosine instance of the argument
     *
     * @param arg Argument of the cosine function
     */
    public static MathFunction cosine(MathFunction arg){
        MathFunction cosine = new Cosine(arg);
        if(cosine.isConstant()){return constant(cosine.evaluate(0));}
        else{return cosine;}
    }

}
