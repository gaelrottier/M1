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

    public Class<?> load(String rep, String name) throws ClassNotFoundException {
        this.rep = rep;
        return loadClass(name);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return super.defineClass(name, b, 0, b.length);
    }

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
