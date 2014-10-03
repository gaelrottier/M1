package fr.miage.m1.tp3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassLoader extends SecureClassLoader {

    private ArrayList<File> path = new ArrayList<File>();

    public void setPath(String... paths) {
        if (paths.length > 0) {
            for (String p : paths) {
                path.add(new File(p));
            }
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        FileInputStream fis = null;
        try {
            String[] className = name.split("\\.");
            File result = null;
            do {
                for (File p : path) {
                    result = checkFile(p, className, 0);
                }
            } while (result == null);

            fis = new FileInputStream(result);
            ByteArrayOutputStream bis = new ByteArrayOutputStream(fis.read());

            return bis.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    private File checkFile(File dir, String[] packageName, int iteration) {

        System.out.println(dir);
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (f.isDirectory()) {
                    if (f.getName().equals(packageName[iteration])) {
                        checkFile(f, packageName, iteration + 1);
                    } else {
                        checkFile(f, packageName, 0);
                    }
                }
            }
        } else if (iteration == packageName.length - 1) {
            return dir;

        }

        return null;

    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader mc = new MyClassLoader();
        mc.setPath(new String[]{"F:\\Bibliothèques\\Documents\\classes"});
        mc.findClass("fr.miage.m1.tp1.q1.FileList");
    }
}
