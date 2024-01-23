package maze;

import java.util.ArrayList;

/**
 * Represents a single cell in the maze
 *
 * @author Nick Salvemini
 */
public class MazeCell {

    /** Coordinates */
    private final Coordinates coordinates;

    /** Neighbors */
    private final ArrayList<MazeCell> neighbors;

    /**
     * Constructor
     *
     * @param coordinates The coordinates of the cell
     */
    public MazeCell(Coordinates coordinates) {
        this.coordinates = coordinates;
        neighbors = new ArrayList<>();
    }

    /**
     * Accessor
     *
     * @return Returns the coordinates of the cell
     * as an instance of the Coordinates record
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Adds an adjacent unblocked cell to the list of
     * neighbors for this cell
     *
     * @param n The neighboring/adjacent cell
     */
    public void addNeighbor(MazeCell n){
        neighbors.add(n);
    }

    /**
     * Accessor for the neighbors
     *
     * @return Returns the list of neighbors
     */
    public ArrayList<MazeCell> getNeighbors(){ return neighbors; }

    /**
     * Gets a printable form of the neighbors of a cell
     *
     * @return Returns a clean String of neighbors
     */
    public String neighborString(){
        StringBuilder str = new StringBuilder();

        int count = 0;
        for(MazeCell nbr : neighbors) {
            str.append(nbr.toString());
            if(count < neighbors.size() - 1) str.append(' ');
        }

        return str.toString();
    }

    /**
     * Uses the coordinates of the cell
     * to describe it in the toString
     *
     * @return Returns the toString of the cells coordinates
     */
    @Override
    public String toString(){
        return coordinates.toString();
    }
}
