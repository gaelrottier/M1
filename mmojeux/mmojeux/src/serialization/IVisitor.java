/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization;

import chargementDynamique.ChargementDynamique;
import ennemi.Ennemi;
import ihm.IHM;
import ihm.IHMEnnemi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import personnage.Arme;
import personnage.Armure;
import personnage.Objet;
import personnage.Personnage;

/**
 *
 * @author Justin
 */
public interface IVisitor {

    public void sauvegarder(Ennemi ennemi);
    public void sauvegarder(Personnage pers);
    public void sauvegarder(Arme arme);
    public void sauvegarder(Armure armure);
    public void sauvegarder(Objet objet);
    
  
    public Ennemi ennemicharger();
    public Personnage personnagecharger();
    public Arme armecharger();
    public Armure armurecharger();
    public Objet objetcharger();
    public ChargementDynamique chargementChargementDynamique() throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException;
}
