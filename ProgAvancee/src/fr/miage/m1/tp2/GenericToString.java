package fr.miage.m1.tp2;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class GenericToString {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

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
                    Class fobjClass = fobj.getClass();
//                    System.out.println("\nclasse field : " + fobjClass);
                    if (isWrapperType(fobjClass)) {
                        System.out.print(" = ");
                        System.out.print(fobj);
                    } else {
                        if (fobjClass.isArray()) {
                            int length = Array.getLength(fobj);
                            System.out.print("{");
                            for (int i = 0; i < length; i++) {
                                Object occurrence = Array.get(fobj, i);

                                if (isWrapperType(occurrence.getClass()) && iteration >= 0) {
                                    System.out.print(occurrence.toString());
                                } else {
                                    toString(occurrence, iteration);
                                }

                                if (i != length - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.print("}");

                        } else if (iteration > 0) {
                            toString(fobj, iteration);
                        }
                    }
                    if (field != fs[fs.length - 1]) {
                        System.out.print("; ");
                    }

                } else {
                    System.out.print(" = null;");
                }
            }

            System.out.print("]");
        }
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
//        GenericToString.toString(new Point(12, 24));
//        System.out.println("\n");
//
        Test t = new Test(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
//
//        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
//        pol.getBounds();
//
        GenericToString.toString(t, 4);
//        System.out.println("\n");
//        GenericToString.toString(pol, 3);
    }
}
