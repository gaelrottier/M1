package fr.miage.m1.tp3.v2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassLoader extends SecureClassLoader {

    public void load(String name) throws ClassNotFoundException {
//        return findClass(name);
        findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        byte[] b = null;

        try {
            File classe = new File(name);
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
        System.out.println(b.length);

        return b;
    }
}
