package fr.miage.m1.tp1.simulator;

import java.util.List;

public class Simulator<T extends IActionable> {

    /**
     * Execution delay in milliseconds
     */
    private volatile int executionDelay;

    /**
     * Animation thread.
     */
    private Thread thread;

    /**
     * A flag for controlling the animation thread
     */
    private volatile boolean running = false;

    protected final List<T> actionables;

    public Simulator(List<T> actionables, int initilaExecutionDelay) {
        this.actionables = actionables;
        setExecutionDelay(initilaExecutionDelay);
    }

    public void start() {
        this.start(false);
    }

    public synchronized void start(final boolean debug) {
        if (running) {
            throw new IllegalStateException("Animation is already running.");
        }

		// the reason we do not inherit from Runnable is that we do not want to
        // expose the void run() method to the outside world. We want to well
        // encapsulate the whole idea of a thread.
        // thread cannot be restarted so we need to always create a new one
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        while (!running) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    try {
                        synchronized (this) {
                            Thread.sleep(executionDelay);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    simulate();
                    if (debug) {
                        System.out.println(actionables);
                    }
                }
            }
        };

        // start the thread
        thread.start();
        // set the flag
        running = true;
    }

    protected void simulate() {
        for (IActionable e : actionables) {
            e.act();
        }
    }

    public synchronized void stop() {
        if (!running) {
            throw new IllegalStateException("Animation is stopped.");
        }
        running = false;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized int getExecutionDelay() {
        return executionDelay;
    }

    public synchronized void setExecutionDelay(int executionDelay) {
        if (executionDelay < 0) {
            throw new IllegalArgumentException(
                    "Execution delay must be greater than zero.");
        }
        this.executionDelay = executionDelay;
    }

}
