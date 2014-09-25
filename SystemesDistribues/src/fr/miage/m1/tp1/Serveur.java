package fr.miage.m1.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serveur extends Thread {

    private final int port;
    private ServerSocket serverSocket;
    private Socket client;
    private String msg;
    private PrintWriter pw;

    Serveur(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Attente d'un client sur le port " + port + "...");
            client = serverSocket.accept();
            pw = new PrintWriter(client.getOutputStream());

            System.out.println("Nouveau client connecté au serveur à partir de " + client.getInetAddress() + " sur le port " + client.getPort());

            do {
                //Lecture
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                msg = br.readLine();
                if (msg.equals("stop")) {
                    System.out.println("Le client " + client.getInetAddress() + " s'est déconnecté.");
                } else {
                    System.out.println("Message du client : " + msg);
                }

                //Ecriture
                pw.println(msg);
                pw.flush();

            } while (msg != null && !msg.isEmpty() && !msg.equals("stop"));

            serverSocket.close();
            client.close();
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

//    public static void main(String[] args) throws IOException {
//
//        if (isNumber(args[0])) {
//            int port = Integer.parseInt(args[0]);
//            Serveur serveur = new Serveur(port);
//            serveur.run();
//        }
//    }
}
