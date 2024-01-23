package canalBoats;

import canalBoats.util.CanalSim;

/**
 * Each LockMaster object belongs to a Lock.
 * Its {@link #run()} method executes in a separate thread.
 * Its job is to wait for Boats to arrive at the Lock (they're in a queue),
 * move them through the Lock (a sleep calculation for this simulation),
 * and release them to their Pilots.
 *
 * @author RIT CS
 * @author Nick Salvemini
 */
public class LockMaster implements Runnable {

    private final String lockName;

    @Override
    public String toString() {
        return lockName + "Master";
    }

    /**
     * A sentinel Boat object that is sent through locks to make their
     * LockMasters stop running.
     * Because it is a single object that everyone uses, ordinary
     * "==" comparison can be used to check for it.
     */
    public static Boat END_BOAT = new Boat( "END", 0 );

    private final Lock lock;

    /**
     * Create a new LockMaster.
     * Called from {@link Lock#Lock(String,int, int)}, since each
     * LockMaster is owned by a Lock.
     * Locks don't have assigned names; they are
     * just assigned sequential IDs, starting at 0.<br>
     * Format: <code>Lock<i>n</i></code>
     * @param lock the Lock to which this LockMaster belongs
     */
    public LockMaster( Lock lock , String lockName) {
        this.lock = lock;
        this.lockName = lockName;
    }

    /**
     * Repeatedly do the following.
     * <ol>
     *     <li>Wait and fetch a Boat off the Lock's queue.</li>
     *     <li>
     *         Calculate the time to go through the lock based on
     *         the boat's length, the lock's length, and the depth
     *         (level change) of the lock. Sleep for that much time.
     *     </li>
     *     <li>Release the Boat ({@link Boat#release()}).</li>
     *     <li>
     *         Calculate the time to do the opposite level change.
     *         Sleep for that much time.
     *     </li>
     * </ol>
     * This method returns after the LockMaster has ferried
     * {@link LockMaster#END_BOAT} through its Lock.
     */
    public void run() {
        CanalSim.println( this + ": I'm on duty." );
        Boat currentBoat;
        do{
            currentBoat = lock.admitNextBoat();

            if(currentBoat != END_BOAT)
                CanalSim.println( this + " helping " + currentBoat + " through " + lock );
            CanalSim.sleep((( currentBoat.getLength() + lock.getLength()) / CanalSim.BOAT_LOCK_SPEED)
                                + (lock.getDepth() / CanalSim.UP_DOWN_SPEED));

            if(currentBoat != END_BOAT){
                CanalSim.println( this + ": " + currentBoat + " is through the lock");
                currentBoat.release();
            }
            else {
                currentBoat.release();
                break;
            }
            CanalSim.sleep(lock.getDepth() / CanalSim.UP_DOWN_SPEED);
        }while(true);
        CanalSim.println( this + ": I'm going off duty!" );
    }
}
