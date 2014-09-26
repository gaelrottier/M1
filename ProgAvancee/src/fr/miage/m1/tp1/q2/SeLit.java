package fr.miage.m1.tp1.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeLit {

    private Matcher m;
    private Pattern p;

    public SeLit() {
        p = Pattern.compile("\\s*//.*");

    }

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
            m = p.matcher(s);
            if (!m.matches()) {
                System.out.println(s);
            }
        }
    }

    static public void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("src/fr/miage/m1/tp1/q2/newfile.java"));
            SeLit sl = new SeLit();
            sl.lecture(sc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SeLit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
