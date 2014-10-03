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
        try {
            FileInputStream fis = null;
            String[] className = name.split("\\.");
            for (File p : path) {
                findFile(p, className, 0);
            }
            fis = new FileInputStream(result);
            ByteArrayOutputStream bis = new ByteArrayOutputStream(fis.read());
            return bis.toByteArray();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void findFile(File dir, String[] packageName, int iteration) {

        if (result == null) {

//            System.out.println("\nL'arborescence de fichiers actuelle est : " + dir.getAbsolutePath());
//            System.out.println("Le fichier recherché est : " + packageName[iteration]);
//            System.out.println("Le fichier courant est : " + dir.getName());
            if (dir.isDirectory()) {

//                System.out.println("  et est un dossier");
                for (File f : dir.listFiles()) {
                    if (result == null) {

//                        System.out.println("\tLe fichier " + f.getName() + ", est présent dans le dossier");
                        if (f.isDirectory()) {

//                            System.out.println("\t  est un dossier");
                            if (f.getName().equals(packageName[iteration])) {

//                                System.out.println("\t\tLe nom du dossier correspond au "
//                                        + "dossier recherché : " + packageName[iteration]
//                                        + ", on incrémente itération à : " + (iteration + 1));
                                findFile(f, packageName, iteration + 1);
                            } else {

//                                System.out.println("\t\tLe nom du dossier ("
//                                        + f.getName() + ") ne correspond pas à celui "
//                                        + "recherché, on remet itération à 0");
                                findFile(f, packageName, 0);
                            }
                        } else {

//                            System.out.println("\t  est un fichier");
                            if (f.getName().equals(packageName[packageName.length - 1] + ".class")) {

//                                System.out.println("\t\tLe nom du fichier (" + f.getName()
//                                        + ") correspond au fichier recherché");
//                                System.out.println("\t\t\tFichier : " + f);
                                result = f;
                            } else {

//                                System.out.println("\t\tLe nom du fichier (" + f.getName()
//                                        + ") ne correspond pas au fichier recherché ("
//                                        + packageName[packageName.length - 1] + ".class)");
//                                
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
        mc.findClass("fr.miage.m1.tp1.q1.FileList");
    }
}
