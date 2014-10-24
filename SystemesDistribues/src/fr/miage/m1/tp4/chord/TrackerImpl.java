package fr.miage.m1.tp4.chord;

import fr.miage.m1.tp4.chord.exceptions.AlreadyRegisteredException;
import java.lang.reflect.Array;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackerImpl implements Tracker {

    private List<Peer> peers;
    private Random random;

    public TrackerImpl(int port) {
        try {
            random = new Random();
            peers = new ArrayList<>();
            Registry r = LocateRegistry.createRegistry(port);
            r.bind("server", this);
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(TrackerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void register(Peer peer) throws AlreadyRegisteredException, RemoteException, AccessException {
        peers.add(peer);
    }

    @Override
    public Peer getRandomPeer() throws RemoteException {
        return peers.get(random.nextInt(peers.size()));
    }
    
    public List<Peer> getPeers(){
        return peers;
    }
}
