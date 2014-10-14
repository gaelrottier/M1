package main;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import ihm.IHM;
import chargementDynamique.GestionPlug;
import ennemi.Ennemi;
import serialization.Serialization;
import personnage.Personnage;
public class mmo {
   /**
	 * @param args
	 */
	public static void main(String[] args) throws MalformedURLException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException, InstantiationException {

                GestionPlug.getInstance();
                Personnage.getInstance();
                Ennemi.getInstance();
                IHM ihm = new IHM();
               
                
                   
        }
 }

