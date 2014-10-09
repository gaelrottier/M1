package fr.miage.m1.tp4.q2;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    private Directory d;

    @Override
    public void run() {
        execute();
    }

    public synchronized void execute() {
        try {
            Registry r = LocateRegistry.getRegistry(10099);
            d = (Directory) r.lookup("server");

            for (int i = 0; i < 10000; i++) {
                System.out.println("Execution dans le thread " + Thread.currentThread().getName());
                d.put("Jean" + i, "1234 " + i);
                System.out.println(d.get("Jean" + (i - 1)));
            }

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        int proc = Runtime.getRuntime().availableProcessors();
        List<Runnable> runnables = new ArrayList<>();

        for (int i = 0; i <= proc; i++) {
            runnables.add(new Client());
        }

        ExecutorService execute = Executors.newFixedThreadPool(proc + 1);

        for (Runnable r : runnables) {
            execute.execute(r);
        }
        execute.shutdown();
    }
}
