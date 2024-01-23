package canalBoats;

import java.util.List;

import canalBoats.util.CanalSim;

/**
 * Each Boat has a Pilot.
 * The Pilot's {@link #run()} method executes in a separate thread.
 * Its job is to drive its Boat through FlatStretches, enqueue it at
 * each Lock, and wait for the Boat to come out of the Lock.
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class Pilot implements Runnable {

    @Override
    public String toString() {
        return boat + "Pilot";
    }

    /** Sequence of Flat Stretches and Locks in the boat's route */
    private final List<Segment> route;
    /** This pilot's boat */
    private final Boat boat;

    /**
     * Create a new Pilot for an already-created Boat.
     * Pilots don't have assigned names; they are
     * just assigned sequential IDs, starting at 0.<br>
     * Format: <code>Pilot<i>n</i></code>
     * @param route the sequence of FlatStretches and Locks through which
     *              the Boat must be piloted.
     * @param boat this Pilot's Boat
     */
    public Pilot( List< Segment > route, Boat boat ) {
        this.route=route;
        this.boat=boat;
        if(!boat.toString().equals("END"))
            CanalSim.println( "New " + this + " has " + boat );
    }

    /**
     * Repeatedly do the following.
     * <ol>
     *     <li>Look up the next Segment of the route.</li>
     *     <li>
     *         Arrive at the Segment, noting whether or not it must
     *         give up its Boat to a LockMaster.
     *         <ul>
     *             <li>
     *                 If so, wait until the Boat says it's out.
     *                 {@link Boat#waitUntilOut()}
     *             </li>
     *             <li>
     *                 If not, simulate moving over water with a
     *                 call to {@link CanalSim#sleep(float)},
     *                 taking into account the Boat length and speed
     *                 and the length of the stretch of water.
     *             </li>
     *         </ul>
     *     </li>
     * </ol>
     * This method returns when the entire route has been traveled.
     */
    public void run() {
        for(Segment seg : route) {
            if(!boat.toString().equals("END"))
                CanalSim.println( this + " is arriving at " + seg );
            if(seg.arrive(boat)){
                boat.waitUntilOut();
                if(!boat.toString().equals("END"))
                    CanalSim.println( this + " has " + boat + " back." );
            } else {
                CanalSim.sleep( (seg.getLength() - boat.getLength()) / CanalSim.BOAT_SPEED );
                if(!boat.toString().equals("END"))
                    CanalSim.println(this + ": " + boat + " is through the stretch.");
            }
        }
    }
}
