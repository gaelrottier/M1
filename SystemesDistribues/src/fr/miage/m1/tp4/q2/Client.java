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

public class Client {

//    private static int i;
//    private static Directory d;
//
//    public Client(int i) {
//        this.i = i;
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("Execution dans le thread " + Thread.currentThread().getName());
//            d.put("Jean " + i, "1234 " + i);
//        } catch (RemoteException | NotBoundException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void execute() {
//        try {
//            Registry r = LocateRegistry.getRegistry(10099);
//            d = (Directory) r.lookup("server");
//
//            for (int i = 0; i < 10000; i++) {
//                System.out.println("Execution dans le thread " + Thread.currentThread().getName());
//                d.put("Jean" + i, String.valueOf(i));
//                System.out.println(d.get("Jean" + (i)));
//            }
//
//        } catch (RemoteException | NotBoundException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) throws RemoteException, NotBoundException {

        int proc = Runtime.getRuntime().availableProcessors();
        List<Runnable> runnables = new ArrayList<>();

//        for (int i = 0; i <= 100000; i++) {
//            runnables.add(new Client(i));
//        }
        ExecutorService execute = Executors.newFixedThreadPool(proc + 1);

        Registry r = LocateRegistry.getRegistry(10099);
        Directory d = (Directory) r.lookup("server");
        
        for (int i = 0; i < 10000; i++) {
            final int y = i;
            execute.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("Put : Execution dans le thread " + Thread.currentThread().getName());
                        d.put("Jean " + y, "1234 " + y);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        for (int z = 0; z < 10000; z++) {
            final int x = z;
            execute.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("Get : Execution dans le thread " + Thread.currentThread().getName());
                        System.out.println(d.get("Jean " + x));
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        execute.shutdown();
    }
}
