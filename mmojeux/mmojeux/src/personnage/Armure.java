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

/** Classe qui gère l'armure  
*/
public class Armure {
    int defense; 
    ChargementDynamique armure;
    File fichier;
    URLClassLoader cl;
    Class<?> classCharged;
    String nomClass;
    Object classInstancie;
    List<Method> listMethode = new ArrayList<Method>();
    private static Armure armor;
    /** Sigleton  
    */
     public static synchronized Armure getInstance(){
		if (armor  == null) {
			armor = new Armure();
		}
		return armor;
	}
    /** Constructeur d'armure  
    */
    private Armure() {
        this.defense = 0;
    }   
    /** méthode qui défini la valeur de l'armure  
    */
     public void setArmure(int armureTemp) {
        this.defense = armureTemp;
        Personnage.getInstance().setDefenseArmure(this.defense);
    }   
    /** méthode qui charge un plugin armure  
    */
    public void Armure(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
               System.out.println("test import arme");
                this.armure = (ChargementDynamique) item;
       
               this.defense =(int) this.armure.getMethodForName("getDefense").invoke(this.armure.getClassInstancie());
               System.out.println("Defense : " + this.defense);
               Personnage.getInstance().setDefenseArmure(this.defense);
                
            
    }
}

