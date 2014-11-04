package fr.miage.m1.TechnoXMLWeb.tp4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * Pour tester un fichier .xsl en ligne de commande. Le résultat de la
 * transformation de truc.xml est affiché sur la sortie standard. Usage : java
 * TestXSL machin.xsl truc.xml.
 */
public class ProcessXSLT {

    public static void process(ServletContext sc) throws TransformerConfigurationException, TransformerException {
        PrintWriter pw = null;
//        File result = null;
        try {
            File styleSheet = new File(sc.getRealPath("/") + "WEB-INF\\zoo.xsl");
            File xmlSource = new File(sc.getRealPath("/") + "WEB-INF\\zoo.xml");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer(new StreamSource(styleSheet));
            String result = "";
            t.transform(new StreamSource(xmlSource), new StreamResult(result));
            pw = new PrintWriter("res.html", "UTF-8");
            pw.write(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessXSLT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProcessXSLT.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            return result;
            pw.close();
        }
    }
}
