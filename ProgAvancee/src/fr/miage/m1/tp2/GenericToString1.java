package fr.miage.m1.tp2;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class GenericToString1 {

    public static void toString(Object o, int... iterations) throws IllegalArgumentException, IllegalAccessException {
        int iteration = iterations.length > 0 ? iterations[0] : 1;
//        System.out.print(" Iteration : "+iteration);
        if (iteration > 0) {
            iteration--;

            Field[] fs = o.getClass().getDeclaredFields();

            System.out.print(" " + o.getClass().getSimpleName() + " [ ");

            for (Field field : fs) {
                field.setAccessible(true);

//                System.out.print(field.getName());
                System.out.print(" " + field.getType().toString() + " ");
                Object fobj = field.get(o);
//                System.out.print(" Type du champs" + fobj.getClass().getSimpleName());
                if (fobj.getClass().isPrimitive() || fobj instanceof Integer || fobj instanceof Boolean || fobj instanceof Double || fobj instanceof Float || fobj instanceof Long) {
                    System.out.print(" = ");
                    System.out.print(field.get(o));
                } else {
                    if (fobj.getClass().isArray()) {
                        String str = "";
                        int length = Array.getLength(fobj);

                        for (int i = 0; i < length; i++) {
                            System.out.print(Array.get(fobj, i).toString());
//                            toString(Array.get(fobj, i), iterations); 
                            
                        }
//                    System.out.println(str);
                    }

//                    System.out.print(Arrays.toString((Object[]) fobj));
                }

                if (field != fs[fs.length - 1]) {
                    System.out.print("; ");
                }
            }
            System.out.print(" ]");
        }else
            System.out.print(o.toString());
    }

    static public void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        GenericToString1.toString(new Point(12, 24));
        System.out.println("\n");

        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
        pol.getBounds();
        GenericToString1.toString(pol, 2);
    }
}
