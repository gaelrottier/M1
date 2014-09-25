package fr.miage.m1.tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleRaceCondition {

    private int counter;

    public synchronized void increment() {
        counter++;

    }

    public synchronized void decrement() {
        counter--;
    }

    public void doSomeWork() {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    decrement();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SimpleRaceCondition.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Counter : " + counter);

    }

    public static void main(String[] args) {
        SimpleRaceCondition src = new SimpleRaceCondition();

        src.doSomeWork();
    }
}
