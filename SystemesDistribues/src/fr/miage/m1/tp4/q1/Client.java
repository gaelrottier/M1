package fr.miage.m1.tp4.q1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.getRegistry(10099);
            IServer is = (IServer) r.lookup("server");
            MyObject mo = new MyObject();
            mo.attrI = 42;

//            Envoi
            System.out.println("Envoi de MyObject au serveur");
            is.take(mo);

//            Récupération
            System.out.println("\nAppel de la méthode sendInt() du serveur");
            System.out.println(is.sendInt());
            System.out.println("\nAppel de la méthode sendMyObject.attrI");
            System.out.println(is.sendMyObject().attrI);
            System.out.println(mo.attrI);
            System.out.println("\nAppel de la méthode test()");
            is.test();
            System.out.println("\nAppel de sendServer");
            System.out.println(is.sendServer().toString());
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
