package fr.miage.m1.tp3.v3;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository<T> {

    private final File rep;
    private final Class<T> superClass;

    public Repository(File rep, Class<T> superClass) {
        this.rep = rep;
        this.superClass = superClass;
    }

    public List<Class<? extends T>> load() {
        List<Class<? extends T>> classes = new ArrayList<>();
        MyClassLoader mcl = new MyClassLoader();

        try {
            Files.walkFileTree(rep.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".class")) {
                        try {
                            //On récupère le nom canonique du 
                            String className = file.toString().substring(rep.toString().length() + 1);
                            //On enlève l'extention de fichier (".class")
                            className = className.substring(0, className.length() - 6);
                            //On remplace les  séparateurs de fichiers par des "."
                            className = className.replace(File.separator, ".");

                            Class<? extends T> c = (Class<? extends T>) mcl.load(rep.toString(), className);

                            if (c.getSuperclass() == superClass) {
                                classes.add(c);
                            }

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
        Repository<Action> rep = new Repository(new File("F:\\Bibliothèques\\Documents\\classes"), Action.class);

        for (Class<? extends Action> c : rep.load()) {
            System.out.println("La classe chargée est : " + c.getName());
        }
    }
}
