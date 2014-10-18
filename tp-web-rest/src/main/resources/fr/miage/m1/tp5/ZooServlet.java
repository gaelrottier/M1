package fr.miage.m1.tp5;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("welcome.txt")
public class ZooServlet {
    
    @GET
    @Produces("text/plain")
    public static void prout(){ 
    }
}
