package main.java.ex3;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(400);
                String productName = (String) queue.take();
                System.out.println(String.format("Consumer take %s", productName));
                System.out.println(String.format("There are %d items in stock", queue.size()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

