package fr.miage.m1.tp4.q1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends UnicastRemoteObject implements IServer {

    private MyObject mo;
    
    Server() throws RemoteException {
    }

    @Override
    public void take(int i) throws RemoteException {
        System.out.println("i : " + i);
    }

    @Override
    public void take(MyObject mo) throws RemoteException {
        this.mo = mo;
        System.out.println("mo.i = " + mo.attrI);
        mo.attrI = 55;
        System.out.println("mo : " + mo.attrI);
    }

    public static void main(String[] args) {
        try {
            Server s = new Server();
            Registry r = LocateRegistry.createRegistry(10099);
            r.rebind("server", s);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int sendInt() throws RemoteException {
        return 57;
    }

    @Override
    public MyObject sendMyObject() throws RemoteException {        
        return mo;
    }

    @Override
    public void takeServerRemote(IServer s) throws RemoteException {
        System.out.println("Server.takeServerRemote() type IServer : " + s.getClass());
    }

    public void takeServer(IServer s) {
        System.out.println("Server.takeServer() type IServer : " + s.getClass());
    }

    @Override
    public void test() throws RemoteException {
        takeServer(this);
        takeServerRemote(this);
    }
    
    @Override
    public IServer sendServer() throws RemoteException{
        return this;
    }
}
