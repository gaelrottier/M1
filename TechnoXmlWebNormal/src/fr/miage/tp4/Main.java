package fr.miage.tp4;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Main {

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {
        String styleSheet = "F:/BILBIO~1/Documents/MIAGE M1 again/TechnoXmlWebNormal/zoo.xsl";
        String xmlSource = "F:\\BIBLIO~1\\Documents\\MIAGE M1 again\\TechnoXmlWebNormal\\zoo.xml";
        TransformerFactory tf = TransformerFactory.newInstance();
        System.out.println(new File(styleSheet).exists());
        Transformer t = tf.newTransformer(new StreamSource(styleSheet));
        t.transform(new StreamSource(xmlSource), new StreamResult(new File("test.html")));
    }
}
