package fr.miage.m1.tp1;

import java.io.File;
import javax.xml.parsers.*;

public class XMLParser {
    
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder parser = factory.newDocumentBuilder();
        parser.parse(new File("src/fr/miage/m1/tp1/zoo.xml"));
    }
    
}
