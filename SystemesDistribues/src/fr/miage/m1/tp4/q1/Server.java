package fr.miage.m1.tp4.q1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends UnicastRemoteObject implements IServer {

    Server() throws RemoteException {
    }

    @Override
    public void take(int i) throws RemoteException {
        System.out.println("i : " + i);
    }

    @Override
    public void take(MyObject mo) throws RemoteException {
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
        MyObject mo = new MyObject();
        mo.attrI = 1337;
        
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
