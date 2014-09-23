package fr.miage.m1.tp1.q1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter implements FilenameFilter {

    private static String[] filters;
    private static Pattern p;
    private static Matcher m;

    public static void setFilters(String[] filters) {
        Filter.filters = filters;
    }

    public static void setFilters(String filters) {
        p = Pattern.compile(filters);
    }

    @Override
    public boolean accept(File dir, String name) {
        boolean accept = false;

        File f = new File(dir.getAbsolutePath() + "/" + name);
        if (filters != null) {
            for (String filter : filters) {
                if (name.endsWith(filter) || f.isDirectory()) {
                    accept = true;
                }
            }
        } else {
            m = p.matcher(name);
            if (m.matches()) {
                accept = true;
            }
        }
        return accept;
    }

}
