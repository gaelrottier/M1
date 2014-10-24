package fr.miage.m1.tp4.chord;

import fr.miage.m1.tp4.chord.exceptions.AlreadyRegisteredException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Main {

    public static void turnAround(Peer peer) {
        try {
            System.out.println("Started turn around from " + peer.getId());

            Peer landMarkPeer = peer;

            System.out.println("Visited " + peer.getId() + " that has " + peer.getPredecessor().getId() + " as predecessor and " + peer.getSuccessor().getId() + " as successor.");

            peer = peer.getSuccessor();
            while (!peer.getId().equals(landMarkPeer.getId())) {
                System.out.println("Visited " + peer.getId() + " that has " + peer.getPredecessor().getId() + " as predecessor and " + peer.getSuccessor().getId() + " as successor.");
                peer = peer.getSuccessor();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws RemoteException, InterruptedException, AlreadyRegisteredException {
        Peer firstPeer = new PeerImpl(new Identifier(0));
        Tracker t = new TrackerImpl(9009);
        Directory d = new DirectoryImpl(t);

        firstPeer.create();
        t.register(firstPeer);

        for (int i = 1; i < 5; i++) {
            Peer peer = new PeerImpl(new Identifier(i * 5));
            peer.join(t.getRandomPeer());
            t.register(peer);
        }

        Thread.sleep(2000);
        turnAround(firstPeer);
        Thread.sleep(2000);
        turnAround(firstPeer);
        Thread.sleep(6000);
        turnAround(firstPeer);

        d.put("Georgeytuizeyrhjlkrhyzjsdlhrekjds", "azoieujk");
        d.put("Marc", "qsdoiuqsd");
        d.put("Jean", "qsdoiu");
        d.put("Pierre", "sqo");
        d.put("Poutrelle", "azeou");
        d.put("Arry", "qsdkjh");

        System.out.println(d.get("Georgeytuizeyrhjlkrhyzjsdlhrekjds"));
        System.out.println(d.get("Marc"));
        System.out.println(d.get("Jean"));
        System.out.println(d.get("Pierre"));
        System.out.println(d.get("Poutrelle"));
        System.out.println(d.get("Arry"));
    }
}
