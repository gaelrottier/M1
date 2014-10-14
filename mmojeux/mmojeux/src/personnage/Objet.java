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

/** Classe qui gère les items  
*/

public class Objet {
    int vie; 
    ChargementDynamique objet;
    File fichier;
    URLClassLoader cl;
    Class<?> classCharged;
    String nomClass;
    Object classInstancie;
    List<Method> listMethode = new ArrayList<Method>();
    private static Objet item;
    /** Sigleton
    */
     public static synchronized Objet getInstance(){
		if (item  == null) {
			item = new Objet();
		}
		return item;
	}
    /** Constructeur  
    */
    private Objet() {
        this.vie = 0;
    }   
    /** méthode qui définit la valeur de la vie objet  
    */
    public void setObjet(int val){
        this.vie = val;
        Personnage.getInstance().setViePotion(this.vie);
    }
    
    /** Méthode qui charge un objet   
    */
    public void Objet(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
               System.out.println("test import Objet");
                this.objet = (ChargementDynamique) item;
       
               this.vie =(int) this.objet.getMethodForName("getVie").invoke(this.objet.getClassInstancie());
               System.out.println("Defense : " + this.vie);
               Personnage.getInstance().setViePotion(this.vie);
    }
    
}
