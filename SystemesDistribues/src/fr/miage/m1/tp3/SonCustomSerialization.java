package fr.miage.m1.tp3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SonCustomSerialization extends ParentCustomSerialization implements Serializable {
    
    public SonCustomSerialization() {
        System.out.println("SonCustomSerialization.SonCustomSerialization()");
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.getI());
        System.out.println("SonCustomSerialization.i = " + this.getI());
        out.defaultWriteObject();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.i = in.readInt();
        System.out.println("SonCustomSerialization.i = " + this.getI());
        in.defaultReadObject();
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        System.out.println("Création de l'objet");
        SonCustomSerialization s = new SonCustomSerialization();
        s.i = 42;
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
        
        SonCustomSerialization sc = (SonCustomSerialization) ois.readObject();
        System.out.println(sc);
    }
    
}
