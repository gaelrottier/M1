package fr.miage.m1.tp4;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("fr.miage.m1.tp4.Menu")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(Menu.class)) {

            Menu menu = elem.getAnnotation(Menu.class);
            String message = "Annotation found in " + elem.getSimpleName() + " with name " + menu.name();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);

            boolean isStatic = false;
            boolean ispublic = false;
            boolean hasNoParameters = false;

            for (Modifier mo : elem.getModifiers()) {
                switch (mo) {
                    case STATIC:
                        isStatic = true;
                        break;
                    case PUBLIC:
                        ispublic = true;
                        break;
                    default:
                        break;
                }
            }

            //elem.asType est une méthode, car l'annotation ne s'applique que sur les méthodes
            //Sans paramètres, elem.asType.toString() == "()<TypeRetour>"
            if ("()".equals(elem.asType().toString().substring(0, 2))) {
                hasNoParameters = true;
            }

            if (isStatic && ispublic && hasNoParameters) {
                try {
                    int ke = 0;

                    switch (menu.shortcut()) {
                        case "A":
                            ke = KeyEvent.VK_A;
                            break;
                        case "Q":
                            ke = KeyEvent.VK_Q;
                            break;
                        case "Z":
                            ke = KeyEvent.VK_Z;
                            break;
                        default:
                            break;
                    }

                    String[] packages = elem.getEnclosingElement().toString().split("\\.");
                    String className = packages[elem.getEnclosingElement().toString().split("\\.").length - 1];

                    Filer f = processingEnv.getFiler();
                    JavaFileObject jfo = f.createClassFile(elem.getEnclosingElement().toString().substring(0, elem.getEnclosingElement().toString().length() - (className.length() + 1)) + ".AbstractAction" + className);
//                    JavaFileObject jfo = f.createSourceFile(elem.getEnclosingElement().toString());

                    PrintWriter p = new PrintWriter(jfo.openWriter());

                    p.write("package " + elem.getEnclosingElement().toString().substring(0, elem.getEnclosingElement().toString().length() - (className.length() + 1)) + ";\n"
                            + "\n"
                            + "import java.awt.event.ActionEvent;\n"
                            + "import java.awt.event.InputEvent;\n"
                            + "import javax.swing.AbstractAction;\n"
                            + "import javax.swing.Icon;\n"
                            + "import javax.swing.KeyStroke;\n"
                            + "import javax.swing.Action;\n"
                            + "\n"
                            + "public class AbstractAction" + className + " extends AbstractAction {\n"
                            + "\n"
                            + "    public AbstractAction" + className + "() {\n"
                            + "        super(\"" + menu.name() + "\", new ImageIcon(\"" + menu.icon() + "\"));\n"
                            + "    }\n"
                            + "\n"
                            + "    @Override\n"
                            + "    public void actionPerformed(ActionEvent e) {\n"
                            + "        new " + elem.getEnclosingElement().toString() + "()." + elem.toString() + ";\n"
                            + "    }\n"
                            + "\n"
                            + "    public Object getValue(String arg0) {\n"
                            + "        if (arg0.equals(Action.ACCELERATOR_KEY)) // cannot be changed later (use putValue when possible - not anonymous)\n"
                            + "        {\n"
                            + "            return KeyStroke.getKeyStroke(" + ke + ", InputEvent.CTRL_DOWN_MASK);\n"
                            + "        }\n"
                            + "        return super.getValue(arg0);\n"
                            + "    }\n"
                            + "}\n"
                            + "");
                    p.flush();
                    p.close();

                } catch (IOException | SecurityException ex) {
                    Logger.getLogger(MyAnnotationProcessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "La methode annotee ne respecte pas les conditions de l'annotation @Menu\n");
            }
        }
        return true;
    }
}
