package ritmath;

import java.util.ArrayList;

/**
 * Represents a product of terms
 *
 * @author Nick Salvemini
 */
public class Product extends AbstractFunction{

    /** List of terms to be multiplied */
    private MathFunction[] terms;

    /**
     * Constructor
     *
     * @param terms Array of terms
     */
    Product(MathFunction... terms){
        super();
        this.terms = terms;
    }

    /**
     * Combines all the constant terms in a
     * Product and moves the combination to the end
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
        MathFunction constantProduct = FunctionFactory.product(constantArr);

        MathFunction[] normalized = new MathFunction[nonConstants.size()+1];
        for(int i = 0; i < normalized.length-1; i++){
            normalized[i] = nonConstants.get(i);
        }
        normalized[normalized.length-1] = FunctionFactory.constant(constantProduct.evaluate(0));
        terms = normalized;

        // Deletes the 1 at the end if there are other terms
        if(normalized.length > 1 &&
                (normalized[normalized.length-1].evaluate(0) == FunctionFactory.constant(1).evaluate(0))){
            MathFunction[] normal = new MathFunction[normalized.length-1];
            System.arraycopy(terms, 0, normal, 0, normal.length);
            terms = normal;
        }
    }

    /**
     * Returns whether the product is constant
     *
     * @return Returns true iff all the terms
     *          being multiplied are constant
     */
    @Override
    public boolean isConstant(){
        boolean isConstant = true;
        for(MathFunction term : terms){if(!term.isConstant()){isConstant = false;}}
        return isConstant;
    }

    /**
     * Evaluates the product for any given
     * value of x
     *
     * @param x The value of x
     * @return Returns the total product
     */
    @Override
    public double evaluate(double x){
        double result = 1;
        for(MathFunction term : terms){result *= term.evaluate(x);}
        return result;
    }

    /**
     * Takes the derivative of the product using
     * the product rule:
     * d(f(x)*g(x))/dx = f'(x)*g(x) + f(x)*g'(x)
     *
     * @return Returns the derivative of the product
     *          as a sum of products
     */
    @Override
    public MathFunction derivative(){
        MathFunction fx = terms[0];
        MathFunction[] rest = new MathFunction[terms.length-1];
        System.arraycopy(terms, 1, rest, 0, terms.length-1);
        MathFunction gx = FunctionFactory.product(rest);

        if(fx.isConstant()){
            return FunctionFactory.product(fx, gx.derivative());
        }else{
            return FunctionFactory.sum(FunctionFactory.product(fx, gx.derivative()),
                    FunctionFactory.product(fx.derivative(), gx));
        }
    }

    /**
     * toString of a Product
     *
     * @return Returns "( Term1 * Term2...)"
     */
    @Override
    public String toString(){
        normalize();
        if(terms.length != 1) {
            StringBuilder termString = new StringBuilder();
                for (int i = 0; i < terms.length; i++) {
                    termString.append(terms[i].toString());
                    if (i < terms.length - 1) {
                        termString.append(" * ");
                    }
                }
            return "( " + termString + " )";
        }else{
            return terms[0].toString();
        }
    }
}
