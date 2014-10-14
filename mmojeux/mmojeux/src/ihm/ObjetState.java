/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import chargementDynamique.ChargementDynamique;
import chargementDynamique.GestionPlug;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import personnage.Arme;

/**
 *
 * @author Justin
 */

/** Listenner du combo qui gère les objets   
 */

public class ObjetState  implements ItemListener{
    public void itemStateChanged(ItemEvent e) {
      if(e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("Potion choisis" + e.getItem());
           if(e.getItem().equals("Aucun")){
              Arme.getInstance().setArme(0);
          }else{
          Object dyn = e.getItem();
          ChargementDynamique charg = null;
          try {
            try {
                  //Personnage.getInstance().Personnage(dyn);
                 charg = GestionPlug.getInstance().chercheObjet(dyn);
                 // Personnage.getInstance().Personnage((ChargementDynamique)e.getItem());
            } catch (InvocationTargetException ex) {
                  Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObjetState.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
 }
}
