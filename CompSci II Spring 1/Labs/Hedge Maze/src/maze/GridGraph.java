package maze;

import java.util.Collection;

/**
 * A graph that is assumed to contain nodes in a 2-dimensional space.
 * Each node therefore is associated with a unique pair of integer coordinates.
 * Some useful display code is also provided.
 */
public abstract class GridGraph {

    /**
     * How many rows of cells in the maze?
     * @return the number of maze rows
     */
    public abstract int numRows();

    /**
     * How many columns of cells in the maze?
     * @return the number of maze columns
     */
    public abstract int numCols();

    /**
     * Look up two cells in the graph based on their coordinates.
     * Answer whether they are connected.
     * @param r1 row of first cell
     * @param c1 column of first cell
     * @param r2 row of second cell
     * @param c2 column of second cell
     * @return true iff there is no barrier between the cells
     */
    public abstract boolean connected( int r1, int c1, int r2, int c2 );

    /**
     * Look up two cells in the graph based on their coordinates.
     * Answer whether they are connected.
     * @param cell1 location of first cell
     * @param cell2 location of second cell
     * @return true iff there is no barrier between the cells
     */
    public boolean connected( Coordinates cell1, Coordinates cell2 ) {
        return connected( cell1.row(), cell1.col(), cell2.row(), cell2.col() );
    }

    /**
     * Is there a node with the given coordinates in this GridGraph?
     * This method is handy for input error checking.
     * @param r row
     * @param c column
     * @return true iff there is a Node in this graph at (r,c)
     */
    public abstract boolean contains( int r, int c );

    /**
     * Determine a path in this GridGraph from one cell to another.
     * @param start the starting coordinates
     * @param end the goal coordinates
     * @return an ordered path of Nodes from start to end, inclusive,
     *         or null if no path exists
     */
    public abstract Collection< Coordinates > findPath(
            Coordinates start, Coordinates end );

    /**
     * Display the maze using vertical bars, horizontal bars, and spaces.
     * Coordinates are also shown above and to the left.
     * This method assumes coordinates are in the range [0,99].
     */
    public void printLayout() {
        // Column coordinates
        System.out.print( "   " );
        for ( int c=0; c<this.numCols(); ++c  ) {
            System.out.printf( " %2d ", c );
        }
        System.out.println();
        System.out.print( "   +" );
        for ( int c=0; c<this.numCols(); ++c  ) {
            System.out.print( "---+" );
        }
        System.out.println();
        // Maze rows, including row coordinates
        for ( int r=0; r<this.numRows(); ++r ) {
            System.out.printf( " %2d|", r );
            Coordinates right = new Coordinates( r, 0 );
            for( int c=0; c<this.numCols(); ++c  ) {
                char barrier;
                if ( c == this.numCols() - 1 ) {
                    barrier = '|';
                }
                else {
                    Coordinates here = right;
                    right = new Coordinates( r, c + 1 );
                    barrier = this.connected( here, right ) ? ' ' : '|';
                }
                System.out.print( "   " + barrier );
            }
            System.out.println();
            // Walls, if any, between rows
            System.out.print( "   +" );

            if ( r < this.numRows() - 1 ) {
                for ( int c=0; c<this.numCols(); ++c  ) {
                    Coordinates here = new Coordinates( r, c );
                    Coordinates below = new Coordinates( r + 1, c );
                    String barrier =
                            this.connected( here, below ) ? "   " : "---";
                    System.out.print( barrier + '+' );
                }
            }
            else {
                for ( int c=0; c<this.numCols(); ++c  ) {
                    System.out.print( "---+" );
                }
            }
            System.out.println();
        }
    }

}
