package fr.miage.m1.tp5.chord;

import fr.miage.m1.tp5.q2.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Directory extends Remote{

    public void put(String nom, String tel) throws RemoteException;
    public String get(String nomRechcerche) throws RemoteException;
}
