package fr.miage.m1.tp4.chord;

import fr.miage.m1.tp4.chord.exceptions.AlreadyRegisteredException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Tracker extends Remote {

    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    Peer getRandomPeer() throws RemoteException;

}
