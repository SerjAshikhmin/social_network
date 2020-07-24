package main.java.ex4;

import java.time.LocalTime;
import java.util.Date;

public class ServiceThread implements Runnable {

    private final long timeInterval;

    public ServiceThread(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(timeInterval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalTime.now());
        }
    }
}
