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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassLoader extends SecureClassLoader {

    private ArrayList<File> path = new ArrayList<File>();
    private File result = null;

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
        byte[] b = null;

        try {
            String[] className = name.split("\\.");

            for (File p : path) {
                findFile(p, className, 0);
            }
            FileInputStream fis = new FileInputStream(result);
            byte[] fileContent = new byte[(int) result.length()];
            int size = fis.read(fileContent);

            ByteArrayOutputStream bis = new ByteArrayOutputStream();
            bis.write(fileContent, 0, size);
            b = bis.toByteArray();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;

    }

    private void findFile(File dir, String[] packageName, int iteration) {

        if (result == null) {
            if (dir.isDirectory()) {
                for (File f : dir.listFiles()) {
                    if (result == null) {
                        if (f.isDirectory()) {
                            if (f.getName().equals(packageName[iteration])) {
                                findFile(f, packageName, iteration + 1);
                            } else {
                                findFile(f, packageName, 0);
                            }
                        } else {
                            if (f.getName().equals(packageName[packageName.length - 1] + ".class")) {
                                result = f;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader mc = new MyClassLoader();
        mc.setPath(new String[]{"F:\\Bibliothèques\\Documents\\classes"});
        System.out.println(mc.findClass("fr.miage.m1.tp1.q2.SeLit").getTypeName());
    }
}
