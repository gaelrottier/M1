package fr.miage.m1.tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements Runnable {

    private String msg;
    private BufferedReader br;
    private PrintWriter pw;
    private Socket client;

    public ClientManager(Socket client) throws IOException {
        this.client = client;
        pw = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        do {
            try {
                //Lecture
                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                msg = br.readLine();
                if (msg.equals("stop")) {
                    System.out.println("Le client " + client.getInetAddress() + " s'est déconnecté.");
                    pw.println("stop");
                    pw.flush();
                } else {
                    System.out.println("Message du client : " + msg);
                    //Ecriture
                    pw.println(msg);
                    pw.flush();

                }
            } catch (IOException ex) {
                Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (!msg.equals("stop"));
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
