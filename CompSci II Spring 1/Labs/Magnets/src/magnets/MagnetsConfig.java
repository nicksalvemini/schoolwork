package magnets;

import backtracking.Configuration;
import test.IMagnetTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The representation of a magnet configuration, including the ability
 * to backtrack and also give information to the JUnit tester.
 *
 * This implements a more optimal pruning strategy in isValid():
 * - Pair checked each time a new cell is populated
 * - Polarity checked each time a new cell is populated
 * - When last column or row is populated, the pos/neg counts are checked
 *
 * @author RIT CS
 */
public class MagnetsConfig implements Configuration, IMagnetTest {

    private final static String WHITESPACE = "\\s+";
    /** a cell that has not been assigned a value yet */
    private final static char EMPTY = '.';
    /** a blank cell */
    private final static char BLANK = 'X';
    /** a positive cell */
    private final static char POS = '+';
    /** a negative cell */
    private final static char NEG = '-';
    /** left pair value */
    private final static char LEFT = 'L';
    /** top pair value */
    private final static char TOP = 'T';
    /** and ignored count for pos/neg row/col */
    private final static int IGNORED = -1;
    /** Expected magnet counts for each row and column */
    private static HashMap<Integer, Integer> posRowExp;
    private static HashMap<Integer, Integer> posColExp;
    private static HashMap<Integer, Integer> negRowExp;
    private static HashMap<Integer, Integer> negColExp;
    /** Board dimensions */
    private static int rows;
    private static int cols;
    /** Magnet board */
    private static char[][] pairs;
    /** Current configuration */
    private final char[][] config;
    /** Cursor row and column */
    private final int row;
    private final int col;
    /** Counters to help pruning efficiency */
    private final HashMap<Integer, Integer> posInRow;
    private final HashMap<Integer, Integer> posInCol;
    private final HashMap<Integer, Integer> negInRow;
    private final HashMap<Integer, Integer> negInCol;

    /**
     * Read in the magnet puzzle from the filename.  After reading in, it should display:
     * - the filename
     * - the number of rows and columns
     * - the grid of pairs
     * - the initial config with all empty cells
     *
     * @param filename the name of the file
     * @throws IOException thrown if there is a problem opening or reading the file
     */
    public MagnetsConfig(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));

        posRowExp = new HashMap<>();
        posColExp = new HashMap<>();
        negRowExp = new HashMap<>();
        negColExp = new HashMap<>();

        posInRow = new HashMap<>();
        posInCol = new HashMap<>();
        negInRow = new HashMap<>();
        negInCol = new HashMap<>();

        // Read first line: rows cols
        String[] fields = in.readLine().split(WHITESPACE);
        rows = Integer.parseInt(fields[0]);
        cols = Integer.parseInt(fields[1]);

        // Initialize counters
        for(int row = 0; row < rows; row++){
            posInRow.put(row, 0);
            negInRow.put(row, 0);
        }
        for(int col = 0; col < cols; col++){
            posInCol.put(col, 0);
            negInCol.put(col, 0);
        }

        row = 0;
        col = -1;
        config = new char[rows][cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                config[row][col] = EMPTY;
            }
        }

        fields = in.readLine().split(WHITESPACE);
        for(int i = 0; i < rows; i++){
            posRowExp.put(i, Integer.parseInt(fields[i]));
        }
        fields = in.readLine().split(WHITESPACE);
        for(int i = 0; i < cols; i++){
            posColExp.put(i, Integer.parseInt(fields[i]));
        }
        fields = in.readLine().split(WHITESPACE);
        for(int i = 0; i < rows; i++){
            negRowExp.put(i, Integer.parseInt(fields[i]));
        }
        fields = in.readLine().split(WHITESPACE);
        for(int i = 0; i < cols; i++){
            negColExp.put(i, Integer.parseInt(fields[i]));
        }

        pairs = new char[rows][cols];

        for(int row = 0; row < rows; row++){
            fields = in.readLine().split(WHITESPACE);
            for(int col = 0; col < cols; col++){
                pairs[row][col] = fields[col].charAt(0);
            }
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.write("File: " + filename +
                        "\nRows: " + rows + ", Columns: " + cols +
                        "\nPairs:\n");
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                out.write(pairs[row][col]);
                if(col < cols-1) out.write(' ');
            }
            out.write('\n');
        }
        out.write("Initial config:\n" + this);
        out.flush();
        }

    /**
     * The copy constructor which advances the cursor, creates a new grid,
     * and populates the grid at the cursor location with val
     * @param other the board to copy
     * @param val the value to store at new cursor location
     */
    private MagnetsConfig(MagnetsConfig other, char val) {
        // Copy config
        config = new char[rows][cols];
        for(int row = 0; row < config.length; row++){
            System.arraycopy(other.config[row], 0, config[row], 0, config[0].length);
        }


        // Advance cursor
        if(other.col == config[0].length-1){
            if(!(other.row == config.length-1)){
                row = other.row + 1;
                col = 0;
            }else{
                row = other.row;
                col = other.col;
            }
        }else{
            row = other.row;
            col = other.col + 1;
        }

        // Change value at cursor
        config[row][col] = val;

        // Get pos/neg counts in rows
        posInRow = new HashMap<>(other.posInRow);
        posInCol = new HashMap<>(other.posInCol);
        negInRow = new HashMap<>(other.negInRow);
        negInCol = new HashMap<>(other.negInCol);

        // Get pos/neg counts for this config
        if(val == POS){
            posInRow.replace(row, posInRow.get(row)+1);
            posInCol.replace(col, posInCol.get(col)+1);
        }
        else if(val == NEG){
            negInRow.replace(row, negInRow.get(row)+1);
            negInCol.replace(col, negInCol.get(col)+1);
        }
    }

    /**
     * Helper function that gets the opposite/corresponding
     * value for any given magnet pairing
     *
     * @param val +, - or X
     * @return Returns the opposite value (+ <-> - , X <-> X)
     */
    private char getOpposite(char val){
        return switch(val){
            case POS -> NEG;
            case NEG -> POS;
            default -> BLANK;
        };
    }

    /**
     * Generate the successor configs.  For minimal pruning, this should be
     * done in the order: +, - and X.
     *
     * @return the collection of successors
     */
    @Override
    public List<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        if(row != rows-1 || col != cols-1) {

            if(col != -1 && pairs[row][col] == LEFT)
                successors.add(new MagnetsConfig(this, getOpposite(config[row][col])));
            else if(col == cols-1 && pairs[row][0] == TOP)
                successors.add(new MagnetsConfig(this, getOpposite(config[row][0])));
            else if(row != 0){
                if(col != cols-1 && pairs[row-1][col+1] == TOP)
                    successors.add(new MagnetsConfig(this, getOpposite(config[row - 1][col + 1])));
                else {
                        successors.add(new MagnetsConfig(this, POS));
                        successors.add(new MagnetsConfig(this, NEG));
                        successors.add(new MagnetsConfig(this, BLANK));
                    }
            } else {
                successors.add(new MagnetsConfig(this, POS));
                successors.add(new MagnetsConfig(this, NEG));
                successors.add(new MagnetsConfig(this, BLANK));
            }

        }
        return successors;
    }


    /**
     * Checks to make sure a successor is valid or not.  For minimal pruning,
     * each newly placed cell at the cursor needs to make sure its pair
     * is valid, and there is no polarity violation.  When the last cell is
     * populated, all row/col pos/negative counts are checked.
     *
     * @return whether this config is valid or not
     */
    @Override
    public boolean isValid() {
        if(config[0][0] == EMPTY) return true;

        if((posRowExp.get(row) != -1 && posInRow.get(row) > posRowExp.get(row)) ||
                (negRowExp.get(row) != -1 && negInRow.get(row) > negRowExp.get(row)) ||
                (posColExp.get(col) != -1 && posInCol.get(col) > posColExp.get(col)) ||
                (negColExp.get(col) != -1 && negInCol.get(col) > negColExp.get(col))) return false;
        if(col == cols-1){
            if((posInRow.get(row) < posRowExp.get(row)) ||
                    (negInRow.get(row) < negRowExp.get(row))) return false;
        }
        char val = config[row][col];
        return checkNeighbors(val);
    }

    /**
     * Helper function for isValid which
     * tells if any neighbors have identical signs
     *
     * @param val Value of cell at cursor
     * @return Returns true iff no adjacent magnets have
     *          the same polarity
     */
    private boolean checkNeighbors(char val){
        if(row == 0){
            if(col == 0) return true;
            return val == BLANK || val != config[row][col-1];
        }else{
            if(col == 0) return val == BLANK || val != config[row-1][col];
            return val == BLANK || (val != config[row-1][col] && val != config[row][col-1]);
        }
    }

    @Override
    public boolean isGoal() {
        return (row == rows-1 && col == cols-1);
    }

    /**
     * Returns a string representation of the puzzle including all necessary info.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // top row
        result.append("+ ");
        for (int col = 0; col < getCols(); ++col) {
            result.append(getPosColCount(col) != IGNORED ? getPosColCount(col) : " ");
            if (col < getCols() - 1) {
                result.append(" ");
            }
        }
        result.append(System.lineSeparator());
        result.append("  ");
        for (int col = 0; col < getCols(); ++col) {
            if (col != getCols() - 1) {
                result.append("--");
            } else {
                result.append("-");
            }
        }
        result.append(System.lineSeparator());

        // middle rows
        for (int row = 0; row < getRows(); ++row) {
            result.append(getPosRowCount(row) != IGNORED ? getPosRowCount(row) : " ").append("|");
            for (int col = 0; col < getCols(); ++col) {
                result.append(getVal(row, col));
                if (col < getCols() - 1) {
                    result.append(" ");
                }
            }
            result.append("|").append(getNegRowCount(row) != IGNORED ? getNegRowCount(row) : " ");
            result.append(System.lineSeparator());
        }

        // bottom row
        result.append("  ");
        for (int col = 0; col < getCols(); ++col) {
            if (col != getCols() - 1) {
                result.append("--");
            } else {
                result.append("-");
            }
        }
        result.append(System.lineSeparator());

        result.append("  ");
        for (int col = 0; col < getCols(); ++col) {
            result.append(getNegColCount(col) != IGNORED ? getNegColCount(col) : " ").append(" ");
        }
        result.append(" -").append(System.lineSeparator());
        return result.toString();
    }

    // IMagnetTest

    @Override
    public int getRows() {return config.length;}

    @Override
    public int getCols() {return config[0].length;}

    @Override
    public int getPosRowCount(int row) {return posRowExp.get(row);}

    @Override
    public int getPosColCount(int col) {return posColExp.get(col);}

    @Override
    public int getNegRowCount(int row) {return negRowExp.get(row);}

    @Override
    public int getNegColCount(int col) {return negColExp.get(col);}

    @Override
    public char getPair(int row, int col) {return pairs[row][col];}

    @Override
    public char getVal(int row, int col) {return config[row][col];}

    @Override
    public int getCursorRow() {return row;}

    @Override
    public int getCursorCol() {return col;}
}
