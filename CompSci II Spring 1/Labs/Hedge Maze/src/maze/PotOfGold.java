package maze;

import java.io.IOException;

/**
 * Hedge Maze graph-search lab main program
 *
 * @author RIT CS
 * February 2022
 */
public class PotOfGold {

    /** If there is a problem with the format of the command line */
    public static final int CMD_LINE_ERROR = 1;
    /** If the maze file cannot be read */
    public static final int MAZE_FILE_ERROR = 2;

    /**
     * Main driver instantiates {@link UserControl} with the name of the maze
     * file, and a GridGraph is built.
     * It then has the {@link UserControl} instance repeatedly prompt for start
     * and finish locations, and print resulting paths using a breadth-first
     * search.
     *
     * If anything goes wrong with the file input, or if the program
     * is unable to read data from standard input (the console), the
     * program exits with a non-zero error code.
     * Error codes are given as static constants in this class.
     *
     * @param args [0]: name of hedge maze description file
     * @see System#exit(int)
     */
    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.out.println( "Usage: java PotOfGold maze-file" );
            System.exit( CMD_LINE_ERROR );
        }
        UserControl userCtrl = null;
        try {
            userCtrl = new UserControl( args[ 0 ] );
        }
        catch( IOException e ) {
            System.out.println( "\nFile " + args[ 0 ] + " could not be read." );
            System.exit( MAZE_FILE_ERROR );
        }

        userCtrl.doUserInput();
    }
}
