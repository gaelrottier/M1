package fr.miage.m1.tp4;

import java.lang.reflect.Method;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("fr.miage.m1.tp3.Menu")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {

    MyAnnotationProcessor() {
        super();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(Menu.class)) {
            Menu menu = elem.getAnnotation(Menu.class);
            String message = "Annotation found in " + elem.getSimpleName() + " with name " + menu.name();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);

            boolean statique = false;
            boolean publique = false;
            boolean sansParametres = true;
            System.out.println("ElementKind : " + ElementKind);
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
            if (elem.getKind() == ElementKind.PARAMETER) {
                sansParametres = false;
            }

        }
        return true;

    }

//    public void process() {
//        // Creation d'un filtre pour ne retenir que les déclarations annotées avec Todo
//        DeclarationFilter annFilter = new DeclarationFilter() {
//            public boolean matches(
//                    Declaration d) {
//                return d.getAnnotation(Todo.class) != null;
//            }
//        };
//
//        // Recherche des entités annotées avec Todo
//        Collection<TypeDeclaration> types = annFilter.filter(env.getSpecifiedTypeDeclarations());
//        for (TypeDeclaration typeDecl : types) {
//            System.out.println("class name: " + typeDecl.getSimpleName());
//
//            Todo todo = typeDecl.getAnnotation(Todo.class);
//
//            System.out.println("description : ");
//            for (String desc : todo.description()) {
//                System.out.println(desc);
//            }
//            System.out.println("");
//        }
//    }
}
