package fr.miage.m1.tp3;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.getRegistry(10099);
            IRemote ir = (IRemote) r.lookup("server");
            System.out.println(ir.getInetAddress());
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
