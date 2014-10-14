/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personnage;

import chargementDynamique.ChargementDynamique;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Justin
 */

/** Classe qui gère les armes  
*/
public class Arme {
    int force; 
    ChargementDynamique arme;
    File fichier;
    URLClassLoader cl;
    Class<?> classCharged;
    String nomClass;
    Object classInstancie;
    List<Method> listMethode = new ArrayList<Method>();
    private static Arme arm;
    /** Sigleton  
    */
     public static synchronized Arme getInstance(){
		if (arm  == null) {
			arm = new Arme();
		}
		return arm;
	}
    /** Constructeur  
    */
    private Arme() {
        this.force = 0;
    }   
    /** méthode qui définit la valeur de l'arme  
    */
    public void setArme(int valeur){
        this.force = valeur;
        Personnage.getInstance().setForceArme(this.force);
    }
    /** méthode qui charge une arme  
    */
    public void Arme(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
               System.out.println("test import arme");
                this.arme = (ChargementDynamique) item;
       
               this.force =(int) this.arme.getMethodForName("getForce").invoke(this.arme.getClassInstancie());
               System.out.println("Force : " + this.force);
               Personnage.getInstance().setForceArme(this.force);
                
            
    }
}
