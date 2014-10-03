package fr.miage.m1.tp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoteObject extends UnicastRemoteObject implements IRemote {

    RemoteObject() throws RemoteException {
    }

    @Override
    public void echo() throws RemoteException {
        System.out.println("Ceci est un test, ceci est un test.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void callInt(int i) throws RemoteException {
        System.out.println(i);
    }

    public static void main(String[] args) {
        try {
            RemoteObject ro = new RemoteObject();
            Registry r = LocateRegistry.createRegistry(10099);
            r.rebind("server", ro);
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getInetAddress() throws RemoteException {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(RemoteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
