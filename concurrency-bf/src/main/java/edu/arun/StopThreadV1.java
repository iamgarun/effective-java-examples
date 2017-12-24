package edu.arun;

import java.util.concurrent.TimeUnit;

/**
 * ITEM 30: SYNCHRONIZE ACCESS TO SHARED MUTABLE DATA
 * <p>
 * Sharing is good; mutable is good; but shared mutability is evil
 * The solution for this problem is available in the successive versions.
 */
public class StopThreadV1 {
    private static boolean stopRequested;

    /**
     * Creates a thread having an internal flag to break itself.
     * The flag is changed external to the thread.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            // This is evil. HotSpot compilers can change it to to optimize itself
            // if(!stopRequested){
            //     while(true){
            //          i++;
            //    }
            // }
            // End result: This will run forever
            // Note: Depending on the compiler, this may run as expected as well.
            while (!stopRequested) {
                i++;
                System.out.println(i);
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1); //Sleep for one second

        stopRequested = true;
    }
}
