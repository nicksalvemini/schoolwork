package canalBoats;

import canalBoats.util.CanalSim;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A canal segment that represents a lock
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class Lock implements Segment {

    /** Lock name, length, and depth */
    private final String name;
    private final int length;
    private final int depth;
    /** Queue of boats for this lock */
    private final Queue<Boat> q;
    /** LockMaster and thread for this lock */
    private final LockMaster master;

    /**
     * Create Lock and set parameters for identification and time calculations.
     * Create a LockMaster for this Lock, and start it running in a
     * separate Thread, waiting for Boats in the Lock's queue.
     * @param name the name of the lock (often a town or a street)
     * @param length length of lock chamber in feet
     * @param depth difference, in feet, of water level between filled
     *              and drained states
     */
    public Lock( String name, int length, int depth ) {
        this.name=name;
        this.length=length;
        this.depth=depth;
        q = new LinkedList<>();

        master = new LockMaster(this, name);
        Thread masterTh = new Thread(master);
        masterTh.start();
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * What is this Lock's length?
     * @return this Lock's length
     */
    @Override
    public int getLength() {
       return length;
    }

    /**
     * What is this Lock's depth?
     * @return this Lock's depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * A Boat has arrived at this Lock.
     * Queue it up (FIFO order) for going through this Lock.
     * The Boat's Pilot calls this method.
     * @param boat the Boat to be enqueued
     * @return true, because this is a Lock, where the Pilot
     *         must give up control of the Boat.
     * @rit.pre boat is not moving through this Lock.
     * @rit.post boat is moving through this Lock.
     */
    @Override
    public synchronized boolean arrive( Boat boat ) {
        if(boat != LockMaster.END_BOAT) {
            CanalSim.println(master + " accepted " + boat);
            CanalSim.println(this + " enqueueing " + boat);
        }
        q.add(boat);
        notify();
        return true;
    }

    /**
     * Bring the next Boat from the Lock's queue into the Lock.
     * The Lock's LockMaster calls this and will do the timing
     * calculation, using {@link Boat#getLength()}.
     * This method will not return until there is a Boat in the
     * queue to remove.
     * @return which Boat got in (FIFO order)
     */
    public synchronized Boat admitNextBoat() {
        while(q.isEmpty())
            try{wait();}catch(InterruptedException e){}
        if(q.peek() != LockMaster.END_BOAT)
            CanalSim.println( this + " dequeueing " + q.peek() );
        return q.remove();
    }
}
