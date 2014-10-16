package fr.miage.m1.tp4.chord;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Main {

    public static void turnAround(Peer peer) {
        try {
            System.out.println("Started turn around from " + peer.getId());

            Peer landMarkPeer = peer;

            peer = peer.getSuccessor();
            while (!peer.getId().equals(landMarkPeer.getId())) {
                System.out.println("Visited " + peer.getId() + " that has " + peer.getPredecessor().getId() + " as predecessor and " + peer.getSuccessor().getId() + " as successor.");
                peer = peer.getSuccessor();
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws RemoteException, InterruptedException {
        Peer firstPeer = new PeerImpl(new Identifier(0));
        firstPeer.create();

        for (int i = 1; i < 5; i++) {
            Peer peer = new PeerImpl(new Identifier(i));
            peer.join(firstPeer);
        }
        
        Thread.sleep(2000);
        turnAround(firstPeer);
    }
}
