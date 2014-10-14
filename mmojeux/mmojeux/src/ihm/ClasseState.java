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
import personnage.Personnage;

/**
 *
 * @author Justin
 */

/** Listenner du combo qui gère les classes   
 */

 class ClasseState implements ItemListener{
    public void itemStateChanged(ItemEvent e) {
      if(e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("Classe choisis" + e.getItem());
          if(e.getItem().equals("Aucun")){
              System.out.println("Set");
              Personnage.getInstance().setVie(0);
              Personnage.getInstance().setForce(0);
              Personnage.getInstance().setNom("Aucun");
              Personnage.getInstance().setAttaque("Aucune");
              Personnage.getInstance().setDefense(0);
              
          }
          else{
          Object dyn = e.getItem();
          ChargementDynamique charg = null;
          try {
            try {
                  //Personnage.getInstance().Personnage(dyn);
                 charg = GestionPlug.getInstance().chercheClasse(dyn);
                 // Personnage.getInstance().Personnage((ChargementDynamique)e.getItem());
            } catch (InvocationTargetException ex) {
                  Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClasseState.class.getName()).log(Level.SEVERE, null, ex);
             }   
    }
      }
    }
 }
 
