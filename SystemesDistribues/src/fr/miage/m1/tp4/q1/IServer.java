package fr.miage.m1.tp4.q1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote{
    
    public void take(int i) throws RemoteException;
    public void take(MyObject mo) throws RemoteException;
    public int sendInt() throws RemoteException;
    public MyObject sendMyObject() throws RemoteException;
    public void takeServerRemote(IServer s) throws RemoteException;
    public void test() throws RemoteException;
    public IServer sendServer() throws RemoteException;
}
