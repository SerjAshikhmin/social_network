package main.java.ex3;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            int i = 0;
            while (true) {
                synchronized (queue) {
                    String productName = String.valueOf(String.format("Product № %d", ++i));
                    queue.put(productName);
                    System.out.println(String.format("Producer add %s", productName));
                    System.out.println(String.format("There are %d items in stock", queue.size()));
                }
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

