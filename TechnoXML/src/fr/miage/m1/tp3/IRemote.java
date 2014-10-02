package fr.miage.m1.tp3;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote{
    public void echo() throws RemoteException;
    public void callInt(int i) throws RemoteException;
    public String getInetAddress() throws RemoteException;
}
