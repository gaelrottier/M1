/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import chargementDynamique.ChargementDynamique;
import chargementDynamique.GestionPlug;
import static ihm.IHMEnnemi.ennemiCombo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */

/** Listenner du combo qui gère les ennemis   
 */
public class ItemAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionListener : action sur " + ennemiCombo.getSelectedItem());
        Object dyn = ennemiCombo.getSelectedItem();
        ChargementDynamique charg = null;
        try {
            try {
                  //Personnage.getInstance().Personnage(dyn);
                 charg = GestionPlug.getInstance().chercheEnnemi(dyn);
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
