package fr.miage.m1.tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {

    private Integer buff;

    public synchronized Integer get() {

        while (buff == null) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Integer save = buff;
        buff = null;
        notifyAll();

        return save;
    }

    public synchronized void put(Integer igr) {
        while (buff != null) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        buff = igr;
        notifyAll();

    }

}
