package edu.arun;

import java.util.concurrent.TimeUnit;

/**
 * ITEM 30: SYNCHRONIZE ACCESS TO SHARED MUTABLE DATA
 * <p>
 * Sharing is good; mutable is good; but shared mutability is evil
 * The is another solution for this problem. Other elegant solutions are available in successive versions.
 * Cooperative thread termination with a volatile field
 */
public class StopThreadV3 {
    private static volatile boolean stopRequested; // Says that  this variable should be cached.

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            // Since it is a method call, it will be properly evaluated
            // Further, both accessor and mutator is sychronized and cooperative
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

