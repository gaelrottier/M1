package fr.miage.m1.tp4.chord;

import java.rmi.RemoteException;
import java.util.List;

public class DirectoryImpl implements Directory {

    private final Tracker t;

    public DirectoryImpl(Tracker t) {
        this.t = t;
    }

    @Override
    public void put(String nom, String tel) throws RemoteException {
        Key k = new Key(nom);

        List<Peer> peers = ((TrackerImpl) t).getPeers();

        for (Peer p : peers) {
            if (k.isBetweenOpenClosed(p.getPredecessor().getId(), p.getId())) {
                p.put(nom, tel);
            }
        }
    }

    @Override
    public String get(String nomRecherche) throws RemoteException {
        Key k = new Key(nomRecherche);
        
        List<Peer> peers = ((TrackerImpl) t).getPeers();
        String num = "";
//        System.out.println("Key : " + k);

        for (Peer p : peers) {
            if (k.isBetweenOpenClosed(p.getPredecessor().getId(), p.getId())) {
                num = "Peer : " + p.getId() + " numéro : " + p.get(nomRecherche);
            }
        }

        return num;
    }

}
