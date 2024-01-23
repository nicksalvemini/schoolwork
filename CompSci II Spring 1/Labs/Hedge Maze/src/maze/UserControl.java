package maze;

import java.io.*;
import java.util.Collection;

/**
 * This class stores the HedgeMaze and gets commands from the user
 * to find paths.
 */
public class UserControl {

    /** What the user types to end the program, instead of new coordinates */
    public static final String USER_DONE = "quit";

    /** The definition of whitespace for the String.split(regex) method */
    public static final String WHITESPACE = "\\s+";

    /** The actual maze, with BFS capability */
    private final HedgeMaze hMaze;

    /**
     * Read in and store the hedge maze description.
     * Display it both as an
     * adjacency list and in character graphics form.
     * @param fileName the hedge maze description file
     * @throws IOException if there is a problem reading the file
     */
    public UserControl( String fileName ) throws IOException {
        hMaze = new HedgeMaze( fileName );
        hMaze.printAdjacencyList();
        System.out.println();
        hMaze.printLayout();
    }

    /**
     * This method repeatedly prompts for start and finish locations
     * and prints the resulting paths using a breadth-first search.
     */
    public void doUserInput() {

        System.out.println();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            try{
                out.write("Enter starting coordinates. (row, then column) ");
                out.flush();
                String[] coordinates = in.readLine().split(WHITESPACE);

                if (coordinates[0].equals(USER_DONE)) break;

                if (!isValid(coordinates).equals("t")) throw new UserInputException(isValid(coordinates));
                String r1 = coordinates[0];
                String c1 = coordinates[1];

                out.write("Enter pot of gold coordinates. (row, then column) ");
                out.flush();
                coordinates = in.readLine().split(WHITESPACE);

                if (coordinates[0].equals(USER_DONE)) break;

                if (!isValid(coordinates).equals("t")) throw new UserInputException(isValid(coordinates));
                String r2 = coordinates[0];
                String c2 = coordinates[1];

                Coordinates start = new Coordinates(r1, c1);
                Coordinates end = new Coordinates(r2, c2);

                out.write("Finding shortest path...\n");
                Collection<Coordinates> path = hMaze.findPath(start, end);
                if (path == null) {
                    out.write("No path was found.\n");
                    out.flush();
                } else {
                    out.write("The path is ");
                    for (Coordinates next : path) {
                        out.write(next.toString());
                        if (!(next.equals(end))) out.write(' ');
                    }
                    out.write('\n');
                    out.flush();
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nQuitting...");
        try{
            in.close();
            out.close();
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Helper function to check user input
     * for validity
     *
     * @param userInput String[] of user input
     * @return Returns the corresponding error string
     *          for three different errors, or "t" if the
     *          input is valid
     */
    private String isValid(String[] userInput){
        try {
            if (userInput.length != 2) return "Wrong number of items given on line.";
            int row = Integer.parseInt(userInput[0]);
            int col = Integer.parseInt(userInput[1]);
            if(!hMaze.contains(row, col))
                return new Coordinates(row, col) + " is not a valid cell location.";
        }catch(NumberFormatException nfe){
            return "Non-integer input.";
        }
        return "t";
    }
}
