package fr.miage.m1.tp2;

import java.lang.reflect.*;
import java.io.*;

public class AnalyseurDeClasse {

    public static void analyseClasse(String nomClasse) throws ClassNotFoundException {
        // Récupération d'un objet de type Class correspondant au nom passé en paramètres
        Class cl = Class.forName(nomClasse);
        afficheEnTeteClasse(cl);

        System.out.println();
        afficheAttributs(cl);

        System.out.println();
        afficheConstructeurs(cl);

        System.out.println();
        afficheMethodes(cl);

        // L'accolade fermante de fin de classe !
        System.out.println("}");
    }

    /**
     * Retourne la classe dont le nom est passé en paramètre
     */
    public static Class getClasse(String nomClasse) throws ClassNotFoundException {
        // CODE A ECRIRE
        return null;
    }

    /**
     * Cette méthode affiche par ex "public class Toto extends Tata implements
     * Titi, Tutu {"
     */
    public static void afficheEnTeteClasse(Class cl) {
        // Affichage du modifier et du nom de la classe

        System.out.print(Modifier.toString(cl.getModifiers()));
        System.out.print(" class " + cl.getSimpleName());
        // Récupération de la superclasse si elle existe (null si cl est le type Object)
        Class supercl = cl.getSuperclass();

        // On ecrit le "extends " que si la superclasse est non nulle et
        // différente de Object
        if (supercl != null && !"Object".equals(supercl.getSimpleName())) {
            System.out.print(" extends " + supercl.getSimpleName());
        }

        // Affichage des interfaces que la classe implemente
        Class[] interfaces = cl.getInterfaces();
        if (interfaces.length != 0) {
            System.out.print(" implements ");
            for (Class inter : interfaces) {
                System.out.print(inter.getSimpleName());
                //Si ce n'est la dernière interface
                if (inter != interfaces[interfaces.length - 1]) {
                    System.out.print(", ");
                }
            }
        }

        // Enfin, l'accolade ouvrante !
        System.out.print(" {\n");
    }

    public static void afficheAttributs(Class cl) {
        Field[] fs = cl.getDeclaredFields();

        System.out.println("\tChamps : {");
        for (Field f : fs) {
            f.setAccessible(true);
            System.out.print("\t" + Modifier.toString(f.getModifiers()));
            System.out.print("\t" + f.getType().getSimpleName());
            System.out.print("\t" + f.getName() + "\n");
        }
        System.out.println("\t}");
    }

    public static void afficheConstructeurs(Class cl) {
        Constructor[] cs = cl.getConstructors();

        System.out.println("\tContructeurs : {");
        for (Constructor c : cs) {
            System.out.println("\t" + c.toGenericString());
        }
        System.out.println("\t}");

    }

    public static void afficheMethodes(Class cl) {
        Method[] ms = cl.getDeclaredMethods();

        System.out.println("\tMéthodes : [");
        for (Method m : ms) {
            System.out.println("\t" + m.toGenericString());
        }
        System.out.println("\t}");

    }

    public static String litChaineAuClavier() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void main(String[] args) {
        boolean ok = false;

        while (!ok) {
            try {
                System.out.print("Entrez le nom d'une classe (ex : java.util.Date): ");
                String nomClasse = litChaineAuClavier();

                analyseClasse(nomClasse);

                ok = true;
            } catch (ClassNotFoundException e) {
                System.err.println("Classe non trouvée");
            } catch (IOException e) {
                System.out.println("Erreur d'E/S!");
            }
        }
    }
}
