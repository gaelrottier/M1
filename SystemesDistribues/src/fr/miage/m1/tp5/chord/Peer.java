package fr.miage.m1.tp5.chord;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Peer extends Remote {

    void startElection() throws RemoteException;
    
    void setLeaderId(Identifier id) throws RemoteException;
    
    Identifier getLeaderId() throws RemoteException;
    
    void setParticipant(boolean participe) throws RemoteException;
    
    boolean getParticipant() throws RemoteException;
    
    void receive(Message message) throws RemoteException;
    
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
