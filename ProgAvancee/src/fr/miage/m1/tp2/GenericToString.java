package fr.miage.m1.tp2;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class GenericToString {

    public static void toString(Object o, int... iterations) throws IllegalArgumentException, IllegalAccessException {
        int iteration = iterations.length > 0 ? iterations[0] : 0;
        iteration--;

        Field[] fs = o.getClass().getDeclaredFields();

        System.out.print(o.getClass() + "[");
        for (Field field : fs) {
            field.setAccessible(true);

            System.out.print(field.getName());
            Object fobj = field.get(o);

            if (fobj.getClass().isPrimitive() || fobj instanceof Integer || fobj instanceof Boolean || fobj instanceof Double || fobj instanceof Float || fobj instanceof Long) {
                System.out.print(" = ");
                System.out.print(field.get(o));
            } else {
                if ("int[]".equals(fobj.getClass().getSimpleName())) {
                    System.out.print(Arrays.toString((int[]) fobj));
                } else if (iteration > 0) {
                    toString(field.get(o), iteration);
                    System.out.print("]");
                }
            }

            if (field != fs[fs.length - 1]) {
                System.out.print("; ");
            }
        }
        System.out.print("]");
    }

    static public void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        GenericToString.toString(new Point(12, 24));
        System.out.println("\n");

        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
        pol.getBounds();
        GenericToString.toString(pol, 2);
    }
}
