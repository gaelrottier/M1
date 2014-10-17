package fr.miage.m1.tp4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Menu {
    String name();
    String icon() default "";
    String shortcut() default "";
    String comportement() default "";
}
