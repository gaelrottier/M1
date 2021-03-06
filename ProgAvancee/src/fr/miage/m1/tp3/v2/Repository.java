package fr.miage.m1.tp3.v2;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {

    private final File rep;

    public Repository(File rep) {
        this.rep = rep;
    }

    public List<Class<?>> load() {
        List<Class<?>> classes = new ArrayList<>();
        MyClassLoader mcl = new MyClassLoader();

        try {
            Files.walkFileTree(rep.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".class")) {
                        try {
                            //On récupère le nom canonique du fichier
                            String className = file.toString().substring(rep.toString().length() + 1);
                            //On enlève l'extention de fichier (".class")
                            className = className.substring(0, className.length() - 6);
                            //On remplace les  séparateurs de fichiers par des "."
                            className = className.replace(File.separator, ".");

                            classes.add(mcl.load(rep.toString(), className));
                        } catch (ClassNotFoundException | SecurityException | IllegalArgumentException ex) {
                            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Repository rep = new Repository(new File("F:\\Bibliothèques\\Documents\\classes"));
        for (Class<?> c : rep.load()) {
            System.out.println("La classe chargée est : " + c.getName());
        }
    }
}
