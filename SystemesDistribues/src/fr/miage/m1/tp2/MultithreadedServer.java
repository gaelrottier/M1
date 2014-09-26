package fr.miage.m1.tp2;

import fr.miage.m1.tp1.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultithreadedServer {

    private final int port;
    private ServerSocket serverSocket;
    private Socket client;

    MultithreadedServer(int port) {
        this.port = port;
    }

    public void execute() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Attente d'un client sur le port " + port + "...");
            while (true) {
                client = serverSocket.accept();
                System.out.println("Nouveau client connecté au serveur à partir de " + client.getInetAddress() + " sur le port " + client.getPort());

                new Thread(new ClientManager(client)).start();

            }
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isNumber(String nb) {
        try {
            Integer.parseInt(nb);
            return true;
        } catch (NumberFormatException nFE) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {

//        if (isNumber(args[0])) {
//            int port = Integer.parseInt(args[0]);
        MultithreadedServer serveur = new MultithreadedServer(10000);
        serveur.execute();
//        }
    }
}
