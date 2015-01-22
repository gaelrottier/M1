package fr.miage.m1.tp3.v3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassLoader extends SecureClassLoader {

    private String rep;

    /**
     * Charge la classe spécifiée par le nom donné en paramètre
     * @param rep Le répertoire contenant la classe
     * @param name Le nom de la classe à charger (de la forme "the.class.to.load", sans le ".class")
     * @return La classe chargée
     * @throws ClassNotFoundException Si la classe n'a pas pu être chargée
     */
    public Class<?> load(String rep, String name) throws ClassNotFoundException {
        this.rep = rep;
        return loadClass(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return super.defineClass(name, b, 0, b.length);
    }

    /**
     * Charge les données de la classe et les renvoie sois la forme de tableau de byte
     * @param name Le nom de la classe à charger (de la forme "the.class.to.load", sans le ".class")
     * @return Le contenu de la classe sous forme de tableau de byte
     */
    private byte[] loadClassData(String name) {
        byte[] b = null;

        try {
            File classe = new File(rep + File.separator + name.replace(".", File.separator) + ".class");
            FileInputStream fis = new FileInputStream(classe);
            byte[] content = new byte[(int) classe.length()];
            int data = fis.read(content);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(content, 0, data);
            b = bos.toByteArray();
            return b;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }
}
