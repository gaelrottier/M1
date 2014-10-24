package bdd;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.basex.BaseXServer;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.server.*;

public class IUCBase {
	private final static String namespace = "declare namespace xsi = \"http://www.w3.org/2001/XMLSchema-instance\";" +
			"declare default element namespace \"http://ref.otcnice.com/webservice/\";" ;
	private static ClientSession session = null;
	public static void connect() throws IOException{
		session = new ClientSession("localhost", 1984, "admin", "admin");
		session.execute(new CreateDB("base", "C:/Users/Administrateur/Desktop/tuto XML/entries_hotels.xml"));
	}
	public static void close() throws IOException{
		session.close();
	}
	public static String getHotel(String fakeExample) throws IOException {
		String result = session.query(namespace+"for $e in doc('base/entries_hotels.xml')//entry  return $e").execute();
		// System.out.println(result);
		if(result == null | result==""){
			result="Pas de résultat";
		}
		return result;
	}
	public static String getHotel(String qHotel, String sLevel) throws IOException {
		String result = session.query(namespace+"for $e in doc('base/entries_hotels.xml')//entry where $e//locations/location = '"+qHotel+"' and substring($e//standings_level, 1, 2) gt '" + sLevel + "'  return $e").execute();
		// System.out.println(result);
		if(result == null | result==""){
			result="Pas de résultat";
		}
		return result;
	}
	public static String getDetailHotel(String ID) throws IOException {
		String result = session.query(namespace+"for $e in doc('base/entries_hotels.xml')//entry where $e/ID = '"+ID+"'  return $e").execute();
		// System.out.println(result);
		if(result == null | result==""){
			result="Pas de résultat";
		}
		return result;
	}
	public static String getAllLocation() throws IOException{
		String result = session.query(namespace+"for $x in let $e := doc('base/entries_hotels.xml')//entry//locations//location return distinct-values($e) return <location>{$x}</location>").execute();
		if(result == null | result==""){
			result="Pas de résultat";
		}
		return result;
	}
	public static Set<Integer> getIds() throws IOException {
	Set<Integer> result = new HashSet<Integer>();

	// Requête
	String query = namespace
			+ "for $hotel in doc('base/entries_hotels.xml')//entry "
			+ "return "
			+ "if("
			+ "count($hotel/latitude) > 0)"
			+ "then string($hotel/ID) " 
			+ "else () ";
	//System.out.println(query);
	String ids = session.query(query).execute();
	if (ids.length() == 0) {
		return result;
	}
	
	String[] res = ids.split(" ");
	
	for (String id : res) {
		try {
			result.add(Integer.parseInt(id));
		} 
		catch (Exception e) {
		}
	}
	System.out.println(result);
	return result;
}
	public static String getLatitude(int id) throws IOException {
		String result = session.query(
				namespace+"string(//entry[ID='"+id+"']/latitude)").execute();
		// System.out.println(result);
		return result;
	}

	public static String getLongitude(int id) throws IOException {
		String result = session.query(
				namespace+"string(//entry[ID='"+id+"']/longitude)").execute();
		// System.out.println(result);
		return result;
	}
	public static String showHotel(int id) throws IOException {
		String result = session.query(
				namespace+"string(//entry[ID='"+id+"']/name_fr_short)").execute();
		// System.out.println(result);
		return result;
	}

}
