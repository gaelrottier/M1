package fr.miage.m1.tp5;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ZooService {

    Templates zooTemplate;

    public ZooService(@Context ServletContext sc) {
        try {
            String xsltPath = sc.getRealPath("WEB-INF/zoo.xsl");
            this.zooTemplate = TransformerFactory.newInstance().newTemplates(new StreamSource(xsltPath));
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ZooService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("/animals.html")
    @Produces("text/html")
    public StreamingOutput getAnimals(
            final @Context ServletContext ctxt,
            final @DefaultValue("true") @QueryParam("listByName") boolean listByName) {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                try {
                    String xmlPath = ctxt.getRealPath("WEB-INF/zoo.xml");
                    Source xmlSource = new StreamSource(xmlPath);
                    Result streamResult = new StreamResult(output);
                    Transformer xslt = ZooService.this.zooTemplate.newTransformer();
                    xslt.setParameter("liste-par-nom", listByName);
                    xslt.transform(xmlSource, streamResult);
                } catch (Exception e) {
                    throw new WebApplicationException(e);
                }
            }
        };
    }

    @GET
    @Path("/welcome.txt")
    @Produces("text/plain")
    public String welcome() {
        return "tatatataatTATTATATAT<h1>TATATATATAT</h1>";
    }
}
