package chargementDynamique;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Justin
 */
public class ChargementDynamique extends SecureClassLoader  {
    File fichier;
    URLClassLoader cl;
    Class<?> classCharged;
    String nomClass;
    Object classInstancie;
    List<Method> listMethode = new ArrayList<Method>();
    public static ArrayList al = new ArrayList();
    /** Constructeur de la classe. */
    public ChargementDynamique(String fileAccess) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		
		this.fichier = new File(fileAccess);
		cl = new URLClassLoader(new URL[] { fichier.toURI().toURL() });
    }
    /** Fonction qui prend chemin (passer à l'aide de JFileChooser) et qui retourne un boolean pour vérifié si oui ou non le chargement du pluggin est ok. 
     * @return boolean qui vérifie si le pluggin est valide 
     */
      public boolean ChargermentJar() throws ClassNotFoundException, InstantiationException, IllegalAccessException  {
        try {
                java.util.jar.JarFile pluggin = new JarFile(fichier);
                System.out.println("Chargement");
                classCharged = cl.loadClass(getNomClasse(pluggin));
                classInstancie = classCharged.newInstance();
                this.listAllMethod();
                System.out.println("Ok");
            } catch (IOException ex) {
            return false;
        }
       
        return true;
    }
    /** Permet de récupéré le nom du pluggin passé en paramètre. 
     * @return String
     */
    private String getNomClasse(JarFile pluggin) {
                Enumeration<JarEntry> enume = pluggin.entries();
                JarEntry nomFichier;
                while (enume.hasMoreElements()) {
                        nomFichier = enume.nextElement();
                        String nomFile = nomFichier.toString();
                        if (nomFile.contains(".class") && !nomFile.contains(".classpath")) {
                                return nomFile.replace("/", ".").substring(0,
                                                nomFile.length() - 6);
                        }
                }
                return null;
        }
    /** Liste les méthodes du pluggin. */
     public void listAllMethod() {
                for (int i = 0; i < classCharged.getDeclaredMethods().length; i++) {
                        listMethode.add(classCharged.getDeclaredMethods()[i]);
                        System.out.println(listMethode.get(i));
                        
                }
        }
     /** Retourne un objet d'un instance de pluggin. 
      * @return objet (pluggin)
      */
     
      public Object getClassInstancie() {
                return classInstancie;
        }
      /** Permet de retourner les méthodes d'un pluggin 
       * @return les methode d'un nom
       */
        public Method getMethodForName(String name) {
                int i = 0;
                while (i < listMethode.size()) {
                        if (listMethode.get(i).getName().equalsIgnoreCase(name)) {

                                return listMethode.get(i);
                        }
                        i++;
                }
                return null;
        }
        
    /** Retourne le nom du pluggin
     * @return String contenant le nom du pluggin
     */
    String getNamePluggin() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        java.util.jar.JarFile pluggin = new JarFile(fichier);
                System.out.println("Chargement");
                classCharged = cl.loadClass(getNomClasse(pluggin));
                classInstancie = classCharged.newInstance();
                this.listAllMethod();
                String name = (String) this.getMethodForName("getNom").invoke(this.getClassInstancie());
                return name;
    }
}