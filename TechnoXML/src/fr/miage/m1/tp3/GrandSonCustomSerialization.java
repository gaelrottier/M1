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
public class GrandSonCustomSerialization extends SonCustomSerialization implements Serializable {

    public GrandSonCustomSerialization() {
        System.out.println("GrandSonCustomSerialization.GrandSonCustomSerialization()");
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.getI());
        System.out.println("GrandSonCustomSerialization.i = " + this.getI());
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.i = in.readInt();
        System.out.println("GrandSonCustomSerialization.i = " + this.getI());
        in.defaultReadObject();
        System.out.println("GrandSonCustomSerialization.f = " + this.f);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Création de l'objet");
        GrandSonCustomSerialization s = new GrandSonCustomSerialization();
        s.setI(42);
        System.out.println("i = " + s.getI());
        System.out.println("-------------------");
        
        //Sérialisation
        System.out.println("Sérialisation");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(s);

        System.out.println("-------------------");

        //Désérialisation
        System.out.println("Désérialisation");
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        GrandSonCustomSerialization gs = (GrandSonCustomSerialization) ois.readObject();
        System.out.println(gs);
    }
}
