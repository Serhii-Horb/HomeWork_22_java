package org.example.Ex2;

public class MyRunnable implements Runnable {
    private final int startThread;
    private final int endThread;
    private final int number;
    private int result;

    public MyRunnable(int startThread, int endThread, int number) {
        this.startThread = startThread;
        this.endThread = endThread;
        this.number = number;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for (int i = startThread; i < endThread; i++) {
            if (i % number == 0) {
                result++;
            }
        }
    }
}
