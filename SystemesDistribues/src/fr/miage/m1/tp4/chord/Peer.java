package fr.miage.m1.tp4.chord;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Peer extends Remote {

    void create() throws RemoteException;

    void join(Peer landmarkPeer) throws RemoteException;

    Peer findSuccessor(Identifier id) throws RemoteException;

    Identifier getId() throws RemoteException;

    Peer getPredecessor() throws RemoteException;

    Peer getSuccessor() throws RemoteException;

    void setPredecessor(Peer peer) throws RemoteException;

    void setSuccessor(Peer peer) throws RemoteException;

    void stabilize() throws RemoteException;

    void notify(Peer peer) throws RemoteException;

    void put(String name, String phoneNumber) throws RemoteException;

    String get(String name) throws RemoteException;

}
