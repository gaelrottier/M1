package fr.miage.m1.TechnoXMLWeb.tp4;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.transform.TransformerException;

@Path("/zoo")
public class ZooServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ServletContext webApp = this.getServletContext();
        try {
            ProcessXSLT.process(webApp);
            response.sendRedirect(webApp.getRealPath("/" + "res.html"));
        } catch (TransformerException ex) {
            throw new ServletException(ex);
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    @GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    @GET
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
