package fr.miage.m1.tp1;

public class RunInThreads {

    public static boolean isNumber(String nb) {
        try {
            Integer.parseInt(nb);
            return true;
        } catch (NumberFormatException nFE) {
            return false;
        }
    }

    public static void main(String[] args) {

        if (isNumber(args[0])) {
            int port = Integer.parseInt(args[0]);
            Serveur serveur = new Serveur(port);
            serveur.start();
            Client client = new Client(port);
            client.start();
        }
    }
}
