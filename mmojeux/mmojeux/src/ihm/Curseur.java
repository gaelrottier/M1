/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Justin
 */

/** Classe qui gère le curseur    
 */

public class Curseur {
     //creer un objet image pour contenir la nouvelle sourie
    Image image = null;
    //constructeur
    public Curseur(Component parent,String img,Point hotSpot){
    //recupere le Toolkit
    Toolkit tk = Toolkit.getDefaultToolkit();
    //sur ce dernier lire le fichier avec "getClass().getRessource" pour
    //pouvoir l'ajouter a un .jar
    image=tk.getImage(getClass().getResource(img));
    //modifi le curseur avec la nouvelle image,en le posissionant grace hotSpot
    //et en lui donnant le nom "X"
    Cursor c=tk.createCustomCursor(image,hotSpot,"X");
    //puis on l'associe au Panel
    parent.setCursor(c);
    }
}
