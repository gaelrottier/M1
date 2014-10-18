package fr.miage.m1.TechnoXMLWeb.tp4;

import javax.servlet.*;
import javax.servlet.http.*;

public class ZooServlet extends HttpServlet {

    public final static String /**
             * The path to the stylesheet.
             */
            XSLT_PATH = "WEB-INF/zoo.xsl",
            /**
             * The path to the XML doc.
             */
            XML_PATH = "zoo.xml";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ServletContext webApp = this.getServletContext();
        try {
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Zoo";
    }

}
