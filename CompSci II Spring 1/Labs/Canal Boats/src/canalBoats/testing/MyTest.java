package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.Arrays;

public class MyTest {

    public static void main(String[] args) {
        Segment level = new FlatStretch(5280);
        Segment lock1 = new Lock("Locko", 80, 32);
        Segment level2 = new FlatStretch(6160);
        Boat geddes = new Boat("Geddes", 20);

        Pilot geddesPilot = new Pilot(Arrays.asList(level, lock1, level2), geddes);
        Thread th = new Thread(geddesPilot, "PilotThread");
        th.start();

        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CanalSim.println("All pilots have finished their routes.");
    }
}
