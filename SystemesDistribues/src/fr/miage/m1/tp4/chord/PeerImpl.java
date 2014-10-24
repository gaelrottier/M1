package fr.miage.m1.tp4.chord;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeerImpl implements Peer {

    private final Identifier id;
    private Map<String, String> entries;
    private Peer successor;
    private Peer predecessor;

    public PeerImpl(Identifier id) {
        this.id = id;
        entries = new ConcurrentHashMap<>();
        successor = this;
        predecessor = this;

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    stabilize();
                } catch (RemoteException ex) {
                    Logger.getLogger(PeerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ses.scheduleAtFixedRate(run, 0, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void create() throws RemoteException {
        successor = this;
    }

    @Override
    public synchronized void join(Peer landmarkPeer) throws RemoteException {
        setSuccessor(landmarkPeer.findSuccessor(id));
    }

    @Override
    public synchronized Peer findSuccessor(Identifier id) throws RemoteException {
        if (this.id.equals(successor.getId())) {
            return this;
        }
        if (id.isBetweenOpenClosed(this.id, successor.getId())) {
            return successor;
        } else {
            return successor.findSuccessor(id);
        }
    }

    @Override
    public Identifier getId() throws RemoteException {
        return this.id;
    }

    @Override
    public Peer getPredecessor() throws RemoteException {
        return predecessor;
    }

    @Override
    public Peer getSuccessor() throws RemoteException {
        return successor;
    }

    @Override
    public void setPredecessor(Peer peer) throws RemoteException {
        predecessor = peer;
    }

    @Override
    public void setSuccessor(Peer peer) throws RemoteException {
        successor = peer;
    }

    @Override
    public synchronized void stabilize() throws RemoteException {
        Peer p = successor.getPredecessor();

        if (p != null && p.getId().isBetweenOpenOpen(id, successor.getId())) {
            successor = p;
        }
        successor.notify(this);
    }

    @Override
    public synchronized void notify(Peer peer) throws RemoteException {
        if (predecessor == null || peer.getId().isBetweenOpenOpen(predecessor.getId(), id)) {
            predecessor = peer;
        }
    }

    @Override
    public synchronized void put(String name, String phoneNumber) throws RemoteException {
        entries.put(name, phoneNumber);
    }

    @Override
    public String get(String name) throws RemoteException {
        return entries.get(name);
    }

    public boolean equals(Peer peer) {
        return this == peer;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
