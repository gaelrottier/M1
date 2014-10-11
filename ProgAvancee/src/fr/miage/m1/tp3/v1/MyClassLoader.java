package fr.miage.m1.tp3.v1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MyClassLoader extends SecureClassLoader {
    
    private final ArrayList<File> path = new ArrayList<>();
    private String result = null;
    
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
            for (File f : path) {
                b = findFile(f.getAbsolutePath(), name);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
        
    }
    
    private byte[] findFile(String dir, String name) throws IOException {
        byte[] b = null;
        
        if (dir.endsWith(".jar") || dir.endsWith(".zip")) {
            
            File f = new File(dir);
            
            if (f.exists()) {
                ZipFile zip = new ZipFile(f);
                Enumeration<? extends ZipEntry> entries = zip.entries();
                
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().equals(name.replace(".", "/") + ".class")) {
                        
                        byte[] array = new byte[1024];
                        InputStream in = zip.getInputStream(entry);
                        ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
                        int length = in.read(array);
                        
                        while (length > 0) {
                            out.write(array, 0, length);
                            length = in.read(array);
                        }
                        
                        b = out.toByteArray();
                    }
                }
            }
        } else {
            
            Path rep = Paths.get(dir);
            
            if (rep.toFile().isDirectory()) {
                Files.walkFileTree(rep, new SimpleFileVisitor<Path>() {
                    
                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {

                        //Si le fichier finit par chemin + package + .class
                        if (file.endsWith(dir + File.separator.toString() + name.replace(".", File.separator.toString()) + ".class")) {
                            MyClassLoader.this.result = file.toString();
                            return FileVisitResult.TERMINATE;
                        }
                        return FileVisitResult.CONTINUE;
                    }
                    
                    //On passe le dossier si l'accès y est interdit
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException io) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    
                });
                System.out.println(result);
                File classe = new File(result);
                FileInputStream fis = new FileInputStream(classe);
                byte[] fileContent = new byte[(int) classe.length()];
                int size = fis.read(fileContent);
                
                ByteArrayOutputStream bis = new ByteArrayOutputStream();
                bis.write(fileContent, 0, size);
                b = bis.toByteArray();
            }
        }
        return b;
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader mc = new MyClassLoader();
        mc.setPath(new String[]{"F:\\Bibliothèques\\Documents\\classes"});
        System.out.println(mc.findClass("fr.miage.m1.tp1.Client").getTypeName());
    }
}
