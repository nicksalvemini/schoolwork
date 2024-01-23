package ritmath;

import java.util.ArrayList;

/**
 * Represents a sum of terms
 *
 * @author Nick Salvemini
 */
public class Sum extends AbstractFunction{

    /** List of terms to be summed up */
    private MathFunction[] terms;

    /**
     * Constructor
     *
     * @param terms Array of terms
     */
    Sum(MathFunction... terms){
        super();
        this.terms = terms;
    }

    /**
     * Combines all the constant terms in a
     * Sum and moves the combination to the end
     */
    private void normalize(){
        ArrayList<MathFunction> nonConstants = new ArrayList<>();
        ArrayList<MathFunction> constants = new ArrayList<>();

        for(MathFunction term : terms){
            if(term.isConstant()){constants.add(term);}
            else{nonConstants.add(term);}
        }

        MathFunction[] constantArr = new MathFunction[constants.size()];
        for(int i = 0; i < constantArr.length; i++){
            constantArr[i] = constants.get(i);
        }
        MathFunction constantSum = FunctionFactory.sum(constantArr);

        MathFunction[] normalized = new MathFunction[nonConstants.size()+1];
        for(int i = 0; i < normalized.length-1; i++){
            normalized[i] = nonConstants.get(i);
        }
        normalized[normalized.length-1] = FunctionFactory.constant(constantSum.evaluate(0));
        terms = normalized;

        // Deletes the 0 at the end if there are other terms
        if(terms.length > 1 &&
                (terms[terms.length-1].evaluate(1) == FunctionFactory.constant(0).evaluate(1))){
            MathFunction[] normal = new MathFunction[terms.length - 1];
            System.arraycopy(terms, 0, normal, 0, normal.length);
            terms = normal;
        }
    }

    /**
     * Returns whether the sum is constant
     *
     * @return Returns true iff all the terms
     *          being summed up are constant
     */
    @Override
    public boolean isConstant(){
        boolean isConstant = true;
        for(MathFunction term : terms){if(!term.isConstant()){isConstant = false;}}
        return isConstant;
    }

    /**
     * Evaluates the sum for any given
     * value of x
     *
     * @param x The value of x
     * @return Returns the total sum
     */
    @Override
    public double evaluate(double x){
        double result = 0;
        for(MathFunction term : terms){result += term.evaluate(x);}
        return result;
    }

    /**
     * Takes the derivative of each term
     * of the sum
     *
     * @return Returns the sum of the derivatives
     *          as a new MathFunction
     */
    @Override
    public MathFunction derivative(){
        MathFunction[] derivativeTerms = new MathFunction[terms.length];
        for(int i = 0; i < terms.length; i++){
            derivativeTerms[i] = terms[i].derivative();
        }
        return FunctionFactory.sum(derivativeTerms);
    }

    /**
     * Evaluates the integral between two bounds
     *
     * @param lower Lower bound
     * @param upper Upper bound
     * @param accuracy Number of rectangles in Riemann sum
     * @return Returns the result of the integral
     */
    @Override
    public double integral(double lower, double upper, int accuracy){
        double result = 0.0;
        for(MathFunction term : terms){
            result += term.integral(lower, upper, accuracy);
        }
        return result;
    }

    /**
     * toString of a Sum
     *
     * @return Returns "( Term1 + Term2... )"
     */
    @Override
    public String toString(){
        normalize();
        if(terms.length != 1) {
            StringBuilder termString = new StringBuilder();
                for (int i = 0; i < terms.length; i++) {
                    termString.append(terms[i].toString());
                    if (i < terms.length - 1) {
                        termString.append(" + ");
                    }
                }
            return "( " + termString + " )";
        }else{
            return terms[0].toString();
        }
    }
}
