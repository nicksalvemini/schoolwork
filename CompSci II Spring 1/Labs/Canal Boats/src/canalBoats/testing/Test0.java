package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test0 {

    /** How many minutes to separate the boats starting by */
    private final static int RELEASE_SEPARATION = 1;

    public static void main(String[] args) {
        Segment level = new FlatStretch( 5280 );
        Segment level1 = new FlatStretch(5720 );
        Segment level2 = new FlatStretch(6160);
        Boat geddes = new Boat( "Geddes", 25 );
        Boat nina = new Boat("Nina", 25);
        Boat pinta = new Boat("Pinta", 25);

        List<Boat> boats = new ArrayList<>();
        boats.add(geddes);
        boats.add(nina);
        boats.add(pinta);

        List<Thread> pilotThreads = new ArrayList<>();
        for(Boat boat : boats) {
            Pilot pilot = new Pilot(Arrays.asList(level, level1, level2), boat);
            Thread th = new Thread(pilot);
            pilotThreads.add(th);
            th.start();
            CanalSim.sleep(RELEASE_SEPARATION);
        }

        for(Thread th : pilotThreads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        CanalSim.println("All pilots have finished their routes.");
    }
}

/*
James Geddes was one of several engineers chosen in 1816 to supervise the
construction of the Erie Canal.
 */
