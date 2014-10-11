package fr.miage.m1.tp3.v2;

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

public class Repository {

    private final File rep;

    public Repository(File rep) {
        this.rep = rep;
    }

    public List<Class<?>> load() {
        List<String> classPath = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();
        MyClassLoader mcl = new MyClassLoader();

        try {
            Files.walkFileTree(rep.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".class")) {
                        try {
                            mcl.load(file.toString());
                        } catch (ClassNotFoundException ex) {
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

//            for (String path : classPath) {
//                System.out.println(path);
//
//                classes.add(mcl.load(path));
//            }

//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger("********* LES CLASSES N'ONT PAS PU ÊTRE CHARGÉES *****************").log(Level.INFO, null, ex);
//            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(classes.toString());
        return classes;
    }

    public static void main(String[] args) {
        Repository rep = new Repository(new File("F:\\Bibliothèques\\Documents\\classes"));
rep.load();
//        for (Class<?> c : rep.load()) {
//            System.out.println("La classe chargée est : " + c.getSimpleName());
//        }
    }
}
