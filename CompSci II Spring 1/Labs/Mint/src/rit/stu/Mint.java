package rit.stu;
import rit.cs.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Module responsible for implementing the Mint language
 * and building the proper parse tree
 *
 * @author Nick Salvemini
 */
public class Mint {

    /**
     * Prints a simple welcome message
     */
    public void displayWelcome(){
        System.out.println("Welcome to Mint language!");
    }

    /**
     * Prints a simple exit message
     */
    public void displayEnd(){
        System.out.println("Quitting Program...");
    }

    /**
     * Takes user input and converts it to an
     * ArrayList of Strings to be parsed through
     *
     * @return Returns the ArrayList of Strings
     */
    public ArrayList<String> getInput(){
        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        String[] values = sc.nextLine().split("\\s++");

        return new ArrayList<>(Arrays.asList(values));
    }

    /**
     * Parses through the tree created by the Mint
     * language and returns the full evaluation of the
     * tree
     *
     * @param tokens An ArrayList of Strings
     * @return Returns the total evaluation of the tree
     */
    public Expression parse(ArrayList<String> tokens){
        String val = tokens.remove(0);
        return switch (val) {
            case "quit" -> null;
            case "+" -> new AddExpression(parse(tokens), parse(tokens));
            case "-" -> new SubExpression(parse(tokens), parse(tokens));
            case "*" -> new MulExpression(parse(tokens), parse(tokens));
            case "/" -> new DivExpression(parse(tokens), parse(tokens));
            case "%" -> new ModExpression(parse(tokens), parse(tokens));
            default -> new IntExpression(Integer.parseInt(val));
        };
    }

    /**
     * Builds a String that represents the whole
     * mathematical expression
     *
     * @param exp The root expression
     * @return Returns the full complex expression,
     *          typed neatly with parenthesis etc. in
     *          the proper order of operation
     */
    public String emit(Expression exp){
        return exp.emit();
    }

    /**
     * Evaluates the whole mathematical expression
     * and returns the resultant integer
     *
     * @param exp The root expression
     * @return Returns the single integer
     */
    public int evaluate(Expression exp){
        return exp.evaluate();
    }

    /**
     * Display messages and run a loop responsible for
     * taking user input as a native array, parsing it
     * into a tree, and emitting and evaluating it
     *
     * @param mint A mint object
     */
    public void runMint(Mint mint){
        mint.displayWelcome();
        do {
            ArrayList<String> mintExpression = mint.getInput();
                Expression expression = parse(mintExpression);
                if(expression != null) {
                    System.out.println("Emit: " + mint.emit(expression));
                    System.out.println("Evaluate: " + mint.evaluate(expression));
                }else{break;}
        }while(true);
        mint.displayEnd();
    }

    /**
     * Create a new mint instance and call the Mint methods
     * to take input, build the parse trees, emit, and evaluate
     * the input expressions
     *
     * @param args System arguments / Run configurations
     */
    public static void main(String[] args) {
        Mint mint = new Mint();
        mint.runMint(mint);
    }
}
