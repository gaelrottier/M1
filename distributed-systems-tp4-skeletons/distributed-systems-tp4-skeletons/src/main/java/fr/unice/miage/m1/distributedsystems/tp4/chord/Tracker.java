package fr.unice.miage.m1.distributedsystems.tp4.chord;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fr.unice.miage.m1.distributedsystems.tp4.chord.exceptions.AlreadyRegisteredException;

public interface Tracker extends Remote {

    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    Peer getRandomPeer() throws RemoteException;

}
