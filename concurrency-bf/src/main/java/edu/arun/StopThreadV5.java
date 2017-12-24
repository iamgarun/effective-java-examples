package edu.arun;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ITEM 30: SYNCHRONIZE ACCESS TO SHARED MUTABLE DATA
 * <p>
 * Sharing is good; mutable is good; but shared mutability is evil
 * The is another solution for this problem. Other elegant solutions are available in successive versions.
 * Using Atomic Integers.
 */
public class StopThreadV5 {
    private static volatile boolean stopRequested; // Says that  this variable should be cached.
    private static AtomicInteger serialNumber;

    public static int generateSerialNumber(){
        return serialNumber.incrementAndGet(); // Use ATOMIC integer to get rid of multi threading effects.
    }
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
