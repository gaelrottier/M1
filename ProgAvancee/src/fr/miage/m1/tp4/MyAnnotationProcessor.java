package fr.miage.m1.tp4;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("fr.miage.m1.tp4.Menu")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(Menu.class)) {

            Menu menu = elem.getAnnotation(Menu.class);
            String message = "Annotation found in " + elem.getSimpleName() + " with name " + menu.name();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);

            boolean statique = false;
            boolean publique = false;
            boolean sansParametres = false;

            for (Modifier mo : elem.getModifiers()) {
                switch (mo) {
                    case STATIC:
                        statique = true;
                        break;
                    case PUBLIC:
                        publique = true;
                        break;
                    default:
                        break;
                }
            }

            TypeMirror t = elem.asType();
            if ("()".equals(t.toString().substring(0, 2))) {
                sansParametres = true;
            }

            if (!statique && !publique && !sansParametres) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "La méthode annotée ne respecte pas les conditions de l'annotation @Menu");
            }
        }
        return true;

    }
}
