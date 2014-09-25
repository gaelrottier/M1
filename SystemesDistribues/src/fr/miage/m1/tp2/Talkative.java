package fr.miage.m1.tp2;

public class Talkative implements Runnable {

    private int cpt;

    public Talkative(int cpt) {
        this.cpt = cpt;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(cpt);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Talkative(i));

            t.start();
        }
    }
}
