package maze;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

/**
 * A coordinate-based graph of nodes to represent a maze laid on a 2-D
 * grid of cells
 * @author Nick Salvemini
 *
 * February 2022
 */
public class HedgeMaze extends GridGraph {

    /** Input file symbol for a barrier between adjacent horizontal cells */
    public final static String VWALL = "|";

    /** Input file symbol for a barrier between adjacent vertical cells */
    public final static String HWALL = "-";

    /** Input file symbol for no barrier between adjacent horizontal cells */
    public final static String NO_WALL = ".";

    /** Number of rows */
    private final int rows;

    /** Number of columns */
    private final int columns;

    /** HashMap representing the cells in the maze */
    private final HashMap<Coordinates, MazeCell> mazeCells;

    /** Array representing existence of a vertical wall between adjacent cells in a row */
    private final boolean[][] vertWalls;

    /** Array representing existence of a horizontal wall between adjacent cells in a column */
    private final boolean[][] horWalls;

    /**
     * Create a graph by reading a file. Details can be found in the lab
     * writeup.
     * @param fileName location of maze specification
     * @rit.pre The format of the file is without errors.
     * @rit.post Graph and Nodes are fully formed with all connections as
     *           specified in the file.
     */
    public HedgeMaze( String fileName ) throws IOException{
        super();
        mazeCells = new HashMap<>();

        BufferedReader read = new BufferedReader(new FileReader(fileName));
            String[] dimensions = read.readLine().split("\\s+");
            rows = Integer.parseInt(dimensions[0]);
            columns = Integer.parseInt(dimensions[1]);

            vertWalls = new boolean[rows][columns-1];
            horWalls = new boolean[rows-1][columns];

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    Coordinates coords = new Coordinates(i, j);
                    mazeCells.put(coords, new MazeCell(coords));
                }
            }

            String[] currentRow;
            for(int i = 0; i < rows; i++){

                currentRow = read.readLine().split("\\s+");

                    int wallCounter = 0;
                for (String s : currentRow) {
                    if (s.equals(NO_WALL)) {
                        vertWalls[i][wallCounter] = false;
                        wallCounter++;
                    } else if (s.equals(VWALL)) {
                        vertWalls[i][wallCounter] = true;
                        wallCounter++;
                    }
                }

                if(i != rows - 1) {
                    currentRow = read.readLine().split("\\s+");

                    for (int j = 0; j < currentRow.length; j++) {
                        if (currentRow[j].equals(NO_WALL)) {
                            horWalls[i][j] = false;
                        } else if (currentRow[j].equals(HWALL)) {
                            horWalls[i][j] = true;
                        }
                    }
                }

            }
        for(MazeCell cell : mazeCells.values())
            buildNeighborList(cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numRows() {
        return rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numCols() {
        return columns;
    }

    /**
     * {@inheritDoc}
     * @rit.pre this.contains( r1, c1 ) and this.contains( r2, c2 )
     */
    @Override
    public boolean connected( int r1, int c1, int r2, int c2 ) {
        if(!areAdjacent(r1 ,c1, r2, c2)) return false;
        if(r1 == r2) {
            if(c1 > c2) return !vertWalls[r1][c2];
            else return !vertWalls[r1][c1];
        } else {
            if(r1 > r2) return !horWalls[r2][c1];
            else return !horWalls[r1][c1];
        }
    }

    /**
     * Tells whether two cells are adjacent
     *
     * @param r1 Row of cell 1
     * @param c1 Column of cell 1
     * @param r2 Row of cell 2
     * @param c2 Column of cell 2
     * @return Returns true iff the cells are adjacent
     */
    public boolean areAdjacent(int r1, int c1, int r2, int c2){
        return (((r1 == r2) && Math.abs(c1 - c2) == 1)
                ||
                (((c1 == c2) && Math.abs(r1 - r2) == 1)));
    }

    /**
     * Adds all neighboring cells to list of
     * neighbors
     *
     * @param cell Maze Cell
     */
    public void buildNeighborList(MazeCell cell){
        for(MazeCell other : mazeCells.values()){
            if(connected(cell.getCoordinates().row(), cell.getCoordinates().col(),
                            other.getCoordinates().row(), other.getCoordinates().col()))
                cell.addNeighbor(other);
        }
    }


    /**
     * Print out detail about this graph.
     * This is done with a heading of<br>
     * &quot;Graph Details:&quot;
     * followed by a blank line, and then
     * printing the details of each of the graph's Nodes, in the Nodes'
     * natural order.
     */
    public void printAdjacencyList() {
        System.out.println( "Graph Details:" + System.lineSeparator() );

        MazeCell tempCell;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tempCell = mazeCells.get(new Coordinates(i, j));
                System.out.println("Cell " + tempCell.toString() +
                        ": " +
                        tempCell.neighborString());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains( int r, int c ) {
        return mazeCells.containsKey(new Coordinates(r,c));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection< Coordinates > findPath( Coordinates start, Coordinates end ) {
        MazeCell startCell = mazeCells.get(start);
        MazeCell endCell = mazeCells.get(end);

        List<MazeCell> q = new LinkedList<>();
        q.add(startCell);

        Map<MazeCell, MazeCell> predecessors = new HashMap<>();
        predecessors.put(startCell, startCell);

        while(!q.isEmpty()){
            MazeCell current = q.remove(0);
            if(current == endCell) break;

            for(MazeCell nbr : current.getNeighbors()){
                if(!predecessors.containsKey(nbr)) {
                    predecessors.put(nbr, current);
                    q.add(nbr);
                }
            }
        }

        if(predecessors.containsKey(endCell)){
            return constructPath(startCell, endCell, predecessors);
        } else return null;
    }

    /**
     * Helper function for findPath
     *
     * @param start Start cell
     * @param end End cell
     * @param predecessors HashMap containing unrepeated,
     *                     ordered neighbors
     * @return Returns a linked list of coordinates
     *          representing the quickest path from start
     *          to finish
     */
    private LinkedList<Coordinates> constructPath
            (MazeCell start, MazeCell end, Map<MazeCell, MazeCell> predecessors){
        LinkedList<Coordinates> path = new LinkedList<>();
        MazeCell current = end;

        while(current != start){
            path.add(0, current.getCoordinates());
            current = predecessors.get(current);
        }
        path.add(0, start.getCoordinates());
        return path;
    }

}
