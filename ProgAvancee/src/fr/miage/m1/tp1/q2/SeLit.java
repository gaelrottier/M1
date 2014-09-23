package fr.miage.m1.tp1.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeLit {

    void lecture(Scanner source) {

        File f = new File("src/fr/miage/m1/tp1/q2/output.txt");
        PrintStream ps;
        try {
            ps = new PrintStream(f);
            System.setOut(ps);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SeLit.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (source.hasNextLine()) {
            String s = source.nextLine();
            System.out.println(s);

        }
    }

    static public void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("src/fr/miage/m1/tp1/q2/SeLit.java"));
            SeLit sl = new SeLit();
            sl.lecture(sc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SeLit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
