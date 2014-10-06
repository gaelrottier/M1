package fr.miage.m1.tp4.q2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.server.UnicastRef;

public class DirectoryImpl extends UnicastRemoteObject implements Directory {

    private Map<String, String> nums = new HashMap<>();

    DirectoryImpl() throws RemoteException{
        
    }            
            
    @Override
    public void put(String nom, String tel) throws RemoteException {
        nums.put(nom, tel);
    }

    @Override
    public String get(String nomRecherche) throws RemoteException {
        return nums.getOrDefault(nomRecherche, null);
    }

    public static void main(String[] args) {
        try {
            Directory d = new DirectoryImpl();
            Registry r = LocateRegistry.createRegistry(10099);
            r.rebind("server", d);
        } catch (RemoteException ex) {
            Logger.getLogger(DirectoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
