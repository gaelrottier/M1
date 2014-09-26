/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.tp2;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gaël
 */
public class XMLParser {

    public static void browseXml(Document doc) {
        doc.getDocumentElement().normalize();
        System.out.println("Root : " + doc.getDocumentElement().getNodeName());
        NodeList nl = doc.getDocumentElement().getChildNodes();

        showChild(nl);
        try {
            serialize(doc, new File("./src/fr/miage/m1/tp2/newzoo.xml"));
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showChild(NodeList nl) {
        Node node;

        for (int i = 0; i < nl.getLength() - 1; i++) {
            node = nl.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (node.getNodeName().equals("nom")) {
                    Element elem = (Element) node.getParentNode();
                    String visite = setVisiteMedicale(
                            node.getParentNode().getAttributes()
                            .getNamedItem("date-naissance").getTextContent());
                    if (visite != null) {
                        elem.setAttribute("visite-medicale",visite);
                    }

                }

                if (node.hasChildNodes()) {
                    showChild(node.getChildNodes());
                }

            }
        }
    }

    public static void serialize(Document doc, File output) throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult(output);
        transformer.transform(source, result);
    }

    public static String setVisiteMedicale(String unSerializedDate) {
        String visiteMedicale = null;

        try {
            DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-M-d");
            Date date = DATE_FORMAT.parse(unSerializedDate);
            Calendar TODAY = Calendar.getInstance();
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.YEAR, 2);
            if (cal.after(TODAY)) {
                visiteMedicale = "Visite à faire";
            }
        } catch (ParseException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return visiteMedicale;
    }

    public static void main(String[] args) throws Exception {

        System.setProperty("file.encoding", "UTF-8");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder parser = factory.newDocumentBuilder();

        browseXml(parser.parse(new File("src/fr/miage/m1/tp2/zoo.xml")));

    }
}
