package fr.miage.m1.tp3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassLoader extends SecureClassLoader {

    private final ArrayList<File> path = new ArrayList<>();
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

            for (File f : path) {
                findFile(f.getAbsolutePath(), name);
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

    private void findFile(String dir, String name) throws IOException {

        if (dir.endsWith(".jar")) {
            JarFile jar = new JarFile(dir);
            
            
        } else {
            
            Path rep = Paths.get(dir);

            if (rep.toFile().isDirectory()) {
                Files.walkFileTree(rep, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {

                        if (file.endsWith(dir + "\\" + name.replace(".", "\\") + ".class")) {
                            MyClassLoader.this.result = file.toFile();
                            return FileVisitResult.TERMINATE;
                        }

                        return FileVisitResult.CONTINUE;

                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException io) {

                        return FileVisitResult.SKIP_SUBTREE;
                    }

                });
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader mc = new MyClassLoader();
        mc.setPath(new String[]{"F:\\Bibliothèques\\Documents\\classes"});
        System.out.println(mc.findClass("fr.miage.m1.tp1.q2.SeLit").getTypeName());
    }
}
