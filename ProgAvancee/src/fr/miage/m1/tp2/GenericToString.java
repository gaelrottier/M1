package fr.miage.m1.tp2;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class GenericToString {

    public static void toString(Object o, int... iterations) throws IllegalArgumentException, IllegalAccessException {
        int iteration = iterations.length > 0 ? iterations[0] : 1;
        if (iteration > 0) {
            iteration--;

            Field[] fs = o.getClass().getDeclaredFields();

            System.out.print(" " + o.getClass().getSimpleName() + "[");
            for (Field field : fs) {
                field.setAccessible(true);

                System.out.print(field.getType().getSimpleName() + " ");
                System.out.print(field.getName());
                Object fobj = field.get(o);
//                System.out.println("Classe  : "+fobj.getClass()+"; SuperClasse : "+fobj.getClass().getSuperclass()  );
                if (fobj != null) {
                    if (fobj.getClass().isPrimitive() || fobj.getClass().getSuperclass().isPrimitive()) {
                        System.out.print(" = ");
                        System.out.print(field.get(o));
                    } else {
                        if (fobj.getClass().isArray()) {
                            int length = Array.getLength(fobj);
                            System.out.print("{");
                            for (int i = 0; i < length; i++) {
                                Object occurrence = Array.get(fobj, i);

                                if (occurrence.getClass().isPrimitive() && iteration >= 0) {
                                    System.out.print(Array.get(fobj, i).toString());
                                } else {
                                    toString(Array.get(fobj, i), iteration);
                                }

                                if (i != length - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.print("}");

                        } else if (iteration > 0) {
                            toString(field.get(o), iteration);
                        }
                    }
                    if (field != fs[fs.length - 1]) {
                        System.out.print("; ");
                    }

                }
            }

            System.out.print("]");
        }
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        GenericToString.toString(new Point(12, 24));
        System.out.println("\n");

        Test t = new Test(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);

        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
        pol.getBounds();

        GenericToString.toString(t, 2);
        System.out.println("\n");
        GenericToString.toString(pol, 2);
    }
}
