/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.tp3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Gaël
 */
public class GrandSon extends Son2 implements Serializable {

    public GrandSon() {
        System.out.println("GrandSon.GrandSon()");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Création de l'objet");
        GrandSon s = new GrandSon();
        s.setI(42);
        System.out.println("i = " + s.getI());
        System.out.println("-------------------");
        //Sérialisation
        System.out.println("Sérialisation");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(s);

        System.out.println("-------------------");
        System.out.println("Désérialisation");
        //Désérialisation
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        GrandSon gs = (GrandSon) ois.readObject();
        System.out.println(gs);
        System.out.println("i = " + gs.getI());
    }
}
