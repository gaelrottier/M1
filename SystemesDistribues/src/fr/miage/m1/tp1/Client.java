package fr.miage.m1.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private Socket socket;
    private final int port;
    private String msg;

    Client(int port) {
        this.port = port;
    }

    public void run() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Vous etes connecté au serveur, veuillez entrer un message à envoyer (\"stop\" pour arrêter) : ");

            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            do {
                //Ecriture
                msg = sc.nextLine();
                pw.println(msg);
                pw.flush();

                //Lecture
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                msg = br.readLine();
                System.out.println("Serveur accuse réception de : \"" + msg + "\"");
            } while (!msg.equals("stop"));

            sc.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
//        if (isNumber(args[0])) {
//            int port = Integer.parseInt(args[0]);
//            Client client = new Client(port);
//            client.run();
//        }
//    }
}
