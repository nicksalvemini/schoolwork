package canalBoats;

/**
 * A level section of a canal, between two locks.
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class FlatStretch implements Segment {

    private static int counter = 0;
    private final int id = counter++;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + this.id;
    }

    private final int length;

    /**
     * Create a new FlatStretch -- the opposite of a Lock.
     * FlatStretches don't have assigned names; they are
     * just assigned sequential IDs, starting at 0.<br>
     * Format: <code>FlatStretch<i>n</i></code>
     * @param length the distance between the stretch's endpoints
     */
    public FlatStretch( int length ) {
        this.length=length;
    }

    /**
     * How long is this stretch of water?
     * @return this object's length, from the constructor
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * Inform this FlatStretch that another Boat has arrived.
     * There is no limit as to how many Boats may be on a FlatStretch.
     * The Boat's Pilot instance calls this and will do the timing
     * calculation, using
     * {@link #getLength()}
     * @param boat the Boat instance (unused in this implementing class)
     * @return false, indicating that this is not a Lock or anything
     *         where the Pilot must give up the boat and wait.
     */
    public boolean arrive( Boat boat ) {
        return false;
    }
}
