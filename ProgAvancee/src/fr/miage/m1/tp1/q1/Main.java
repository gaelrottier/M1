package fr.miage.m1.tp1.q1;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static String strJoin(String[] aArr, String sSep) {
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.length; i < il; i++) {
            if (i > 0) {
                sbStr.append(sSep);
            }
            sbStr.append(aArr[i]);
        }
        return sbStr.toString();
    }

    String str = "";

    private File dir;

    public Main() {
    }

//    public void listerFichiers1(String rep, String tab) {
//        dir = new File(rep);
//        File[] files = dir.listFiles();
//
//        for (File file : files) {
//
//            if (file.isDirectory()) {
//                System.out.println(tab + "rep " + file.getName());
//                listerFichiers1(file.getAbsolutePath(), tab + "\t");
//            } else {
//                System.out.println(tab + "fil " + file.getName());
//            }
//        }
//    }
//
//    public void listerFichiers2(String rep, String tab) {
//        dir = new File(rep);
//        File[] files = dir.listFiles(new Filter());
//
//        for (File file : files) {
//
//            if (file.isDirectory()) {
//                System.out.println(tab + "rep " + file.getName());
//                listerFichiers2(file.getAbsolutePath(), tab + "\t");
//            } else {
//                System.out.println(tab + "fil " + file.getName());
//            }
//        }
//    }
//
//    public void listerFichiers3(String rep, String tab) {
//        dir = new File(rep);
//        try {
//            File[] files = dir.listFiles(new fr.miage.m1.tp1.Main.Filter());
//
//            for (File file : files) {
//
//                if (file.isDirectory()) {
//                    System.out.println(tab + "rep " + file.getName());
//                    listerFichiers3(file.getAbsolutePath(), tab + "\t");
//                } else {
//                    System.out.println(tab + "fil " + file.getName());
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    static class Filter implements FilenameFilter {
//
//        private static String[] filters;
//
//        public static void setFilters(String[] filters) {
//            Filter.filters = filters;
//        }
//
//        @Override
//        public boolean accept(File dir, String name) {
//            boolean accept = false;
//
//            File f = new File(dir.getAbsolutePath() + "/" + name);
//
//            for (String filter : filters) {
//                if (name.endsWith(filter) || f.isDirectory()) {
//                    accept = true;
//                }
//            }
//            return accept;
//        }
//
//    }
//
//    public void listerFichiers4(String rep, String tab, String[] filters) {
//        dir = new File(rep);
//        try {
//            FilenameFilter ff = new FilenameFilter() {
//
//                private String[] filters;
//
//                public FilenameFilter setFilters(String[] filters) {
//                    this.filters = filters;
//                    return this;
//                }
//
//                @Override
//                public boolean accept(File dir, String name) {
//                    boolean accept = false;
//
//                    File f = new File(dir.getAbsolutePath() + "/" + name);
//
//                    for (String filter : filters) {
//                        if (name.endsWith(filter) || f.isDirectory()) {
//                            accept = true;
//                        }
//                    }
//                    return accept;
//                }
//
//            }.setFilters(filters);
//
//            File[] files = dir.listFiles(ff);
//
//            for (File file : files) {
//
//                if (file.isDirectory()) {
//                    System.out.println(tab + "rep " + file.getName());
//                    listerFichiers4(file.getAbsolutePath(), tab + "\t", filters);
//                } else {
//                    System.out.println(tab + "fil " + file.getName());
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
    public static void main(String[] args) throws IOException {

        Main rep = new Main();

//      Sans filtre
//        System.out.println("Liste des fichiers du dossier \".\"");
//        rep.listerFichiers1(".", "");
        //
//      Avec filtre
//        String[] filters = new String[]{".class", ".xml"};
//        Filter.setFilters(filters);
//
//        System.out.println("Liste des fichiers du dossier \".\" dont l'extension de fichier est " + strJoin(filters, ", ")));
//        rep.listerFichiers2(".", "");
//      Avec InnerClass
//        String[] filters = new String[]{".class", ".xml"};
//        Filter.setFilters(filters);
//
//        System.out.println("Liste des fichiers du dossier \".\" dont l'extension de fichier est " + strJoin(filters, ", "));
//        rep.listerFichiers3(".", "");
//      Avec AnonymousInnerClass
//        String[] filters = new String[]{".class", ".xml"};
//
//        System.out.println("Liste des fichiers du dossier \".\" dont l'extension de fichier est " + strJoin(filters, ", "));
//        rep.listerFichiers4(".", "", filters);
//
//      Avec Files et SimpleFileVisitor
//        String[] filters = new String[]{".class"};
//        Filter.setFilters(filters);
//        Path startingDir = Paths.get(".");
//        Files.walkFileTree(startingDir, new FileList());
//      
//      Avec regex
        String filter = ".*\\.(class|xml)";
        Filter.setFilters(filter);
        Path startingDir = Paths.get(".");
        Files.walkFileTree(startingDir, new FileList());
    }

}

class FileList extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) {
        Filter filter = new Filter();
        if (filter.accept(dir.toFile().getParentFile(), dir.toFile().getName())) {
            System.out.println("\tfil " + dir.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
        System.out.println("dir " + dir.toFile().getPath());
        return FileVisitResult.CONTINUE;
    }
}
