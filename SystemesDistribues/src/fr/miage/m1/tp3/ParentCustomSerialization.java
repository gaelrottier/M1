package fr.miage.m1.tp3;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParentCustomSerialization {

    protected int i;
    public File f = new File("toto.txt");

    public ParentCustomSerialization() {
        System.out.println("ParentCustomSerialization.ParentCustomSerialization()");
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.getI());
        System.out.println("ParentCustomSerialization.i = " + this.getI());
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.i = in.readInt();
        System.out.println("ParentCustomSerialization.i = " + this.getI());
        in.defaultReadObject();
    }
}
