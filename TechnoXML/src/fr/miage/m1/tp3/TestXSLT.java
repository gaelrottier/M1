package fr.miage.m1.tp3;

import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * Pour tester un fichier .xsl en ligne de commande.
 * Le résultat de la transformation de truc.xml
 * est affiché sur la sortie standard.
 * Usage : java TestXSL machin.xsl truc.xml.
 */
public class TestXSLT {
  public static void main(String[] args) 
    throws TransformerConfigurationException, TransformerException {
    String styleSheet = "F:/BIBLIO~1/Documents/MIAGE M1 again/TechnoXML/src/fr/miage/m1/tp3/zoo.xsl";
    String xmlSource = "F:/BIBLIO~1/Documents/MIAGE M1 again/TechnoXML/src/fr/miage/m1/tp3/zoo.xml";
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer t = tf.newTransformer(new StreamSource(styleSheet));
    t.transform(new StreamSource(xmlSource),
	       new StreamResult(new File("test.html     ")));
  }
}
    
