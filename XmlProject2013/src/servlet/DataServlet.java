package servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import bdd.IUCBase;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
		try {

			IUCBase.connect();
			fillSelect(IUCBase.getAllLocation(),"search.xsl","C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/search.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
 catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("value") != null){
			String c = IUCBase.getDetailHotel(request.getParameter("value"));
			writeXML(c);
			request.setAttribute("test",c);
			
			try {
				applyTpl("C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/temp.xml","C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/WEB-INF/tpl/hotelDetail.xsl");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher( "index.jsp" ).forward( request, response );
		}
		
			String reponse = affichageHotels(request);
			
			response.getWriter().print(reponse);

		

			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getParameter("hotel")){
		case "search":
				String cc = IUCBase.getHotel(request.getParameter("qHotel"),request.getParameter("sLevel"));
				writeXML(cc);
				request.setAttribute("test",cc);
				
				try {
					applyTpl("C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/temp.xml","C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/WEB-INF/tpl/hotelMatch.xsl");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher( "index.jsp" ).forward( request, response );
			if (request.getParameter("qHotel") == "test"){
				
			}
			break;
		case "all" :
			String c = IUCBase.getHotel("test");
			writeXML(c);
			request.setAttribute("test",c);
			
			try {
				applyTpl("C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/temp.xml","C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/WEB-INF/tpl/hotelMatch.xsl");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher( "index.jsp" ).forward( request, response );
			break;
			default : ;
		}
		
	}
	private void writeXML(String xml) throws IOException{
		String adresse = "C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/temp.xml";

		File f = new File(adresse);
		
		String s = "<?xml version='1.0' encoding='UTF-8'?>";
		String s3 = "<entries xmlns=\"http://ref.otcnice.com/webservice/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:SchemaLocation=\"http://ref.otcnice.com/webservice/entries.xsd\">";

		OutputStreamWriter OS = new OutputStreamWriter(new FileOutputStream(adresse,false),"UTF-8");
		PrintWriter output = new PrintWriter(new BufferedWriter(OS));
		output.println(s+"\n"+s3+"\n");
		output.write(xml+"\n </entries>"); 
		output.close(); 
		
	}
	private void applyTpl(String xmlPath,String xslPath) throws TransformerConfigurationException{
		TransformerFactory fabrique;
		StreamSource xml;
		StreamSource xslt;
		StreamResult dest;
		Transformer transformation;

		fabrique = TransformerFactory.newInstance();
		xml = new StreamSource(new File(xmlPath));
		xslt = new StreamSource(new File(xslPath));
		transformation = fabrique.newTransformer(xslt);
		File content = new File("C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/content.html");
		
		dest = new StreamResult(content);
		try {
			transformation.transform(xml, dest);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void applyTplsearch(String xmlPath,String xslPath) throws TransformerConfigurationException{
		TransformerFactory fabrique;
		StreamSource xml;
		StreamSource xslt;
		StreamResult dest;
		Transformer transformation;
		System.out.println("Here goo");
		fabrique = TransformerFactory.newInstance();
		xml = new StreamSource(new File(xmlPath));
		xslt = new StreamSource(new File(xslPath));
		transformation = fabrique.newTransformer(xslt);
		System.out.println("Here goog");
		File content = new File("C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/menu.html");
		
		dest = new StreamResult(content);
		try {
			transformation.transform(xml, dest);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void fillSelect(String xml, String xslname,String cible) throws UnsupportedEncodingException, FileNotFoundException, TransformerConfigurationException{
		String adresse = cible;
		
		File f = new File(adresse);
		
		String s = "<?xml version='1.0' encoding='UTF-8'?>";
		String s3 = "<entries xmlns=\"http://ref.otcnice.com/webservice/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:SchemaLocation=\"http://ref.otcnice.com/webservice/entries.xsd\">";

		OutputStreamWriter OS = new OutputStreamWriter(new FileOutputStream(adresse,false),"UTF-8");
		PrintWriter output = new PrintWriter(new BufferedWriter(OS));
		output.println(s+"\n"+s3+"\n");
		output.write(xml+"\n </entries>"); 
		output.close(); 
		System.out.println("Here go");
		applyTplsearch(adresse,"C:/Users/Administrateur/workspace/ProjetXML_M1/WebContent/WEB-INF/tpl/"+xslname);
	}
	public String affichageHotels(HttpServletRequest request)
			throws IOException {
		String reponse = "<script> $(function() { ";
		Set<Integer> ids = IUCBase.getIds();
		for (Integer id : ids) {
			reponse += "var contentString" + id +" =\"" + IUCBase.showHotel(id) + "\"; \n"
					+ "var infowindow" + id +" = new google.maps.InfoWindow({content: contentString"+ id +"}); \n"
					+ "var monMarker" + id +" = new google.maps.Marker({"
					+ "position: new google.maps.LatLng("
					+ IUCBase.getLatitude(id) +" , "
					+ IUCBase.getLongitude(id) +"),"
					+ "map: map,"
					+ "title:\"" + IUCBase.showHotel(id) + "\""
					+ "}); \n"
					+ "google.maps.event.addListener(monMarker"+ id +", 'click', function() {"
							+ "infowindow" + id +".open(map,monMarker"+ id +")}); \n" ;
					}
		reponse += "});</script>";
		System.out.println("j'ai fini");
		System.out.println(reponse);
		return reponse;
	}

}
