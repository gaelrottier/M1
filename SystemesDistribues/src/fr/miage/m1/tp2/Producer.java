package fr.miage.m1.tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    private Buffer buff;
    private int id;

    public Producer(Buffer buff, int id) {
        this.buff = buff;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            buff.put(i);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Id producer = " + id + " Valeur insérée = " + i);
        }
    }
}
