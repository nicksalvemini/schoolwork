package canalBoats;

/**
 * The code that simulates a canal boat.
 * It knows its length, thus allowing for calculations regarding
 * how long it takes to move from one place to another.
 * More importantly, it has two states: moving through a lock, or
 * traveling through a flat stretch.
 * For each Lock, there is a queue of waiting boats and a LockMaster
 * who moves boats through the lock. It is assumed that during this
 * time the boat's Pilot is suspended waiting for the method
 * {@link #waitUntilOut()} to return, meaning this Boat is out of
 * the lock.
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class Boat {

    private final String name;
    private final int length;

    /**
     * Initialize the boat with its name and dimensions.
     * @rit.post This Boat is not moving through a lock.
     * @param name Boat's string name, for simulation reporting
     * @param length Boat's length for timing calculations
     */
    public Boat( String name, int length ) {
        this.name=name;
        this.length=length;
    }

    /** Return the length of the boat */
    public int getLength() {
        return length;
    }

    /** Return the name of the boat */
    @Override
    public String toString() {
        return name;
    }

    /**
     * This Boat's
     * Pilot calls this to wait for their boat to exit the lock.
     * @rit.pre This Boat is moving through a lock (enqueued and
     *          hasn't come out).
     */
    public synchronized void waitUntilOut() {
        try {
            wait();
        }catch(InterruptedException e){}
    }

    /**
     * The lock master calls this method to signal that
     * the boat is through the lock, and the pilot can
     * have the boat back.
     * @rit.pre This Boat is moving through a lock.
     * @rit.post This Boat is not moving through a lock.
     */
    public synchronized void release() {
        notify();
    }
}
