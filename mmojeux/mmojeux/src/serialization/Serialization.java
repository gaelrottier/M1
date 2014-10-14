/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization ;

import chargementDynamique.ChargementDynamique;
import ennemi.Ennemi;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import personnage.Arme;
import personnage.Armure;
import personnage.Objet;
import personnage.Personnage;

/**
 *
 * @author Justin
 */
public class Serialization implements IVisitor {
        FileOutputStream fichierOUT;
        FileInputStream fichierIN;
        ObjectInputStream ois;
        ObjectOutputStream oos;
    
    public Serialization(FileInputStream fichierIN) throws IOException {
                // TODO Auto-generated constructor stub

                this.fichierIN = fichierIN;
                // this.oos = new ObjectOutputStream(fichierOUT);
                this.ois = new ObjectInputStream(fichierIN);
        
    }   
     public Serialization(FileOutputStream fichierOUT) throws IOException {
                // TODO Auto-generated constructor stub
                // fichierOUT = new FileOutputStream("./SaveJeux.ser");
                this.fichierOUT = fichierOUT;
                // this.oos = new ObjectOutputStream(fichierOUT);
                this.oos = new ObjectOutputStream(fichierOUT);

        }
   
    @Override
    public void sauvegarder(Ennemi e) {
            try {
                        FileOutputStream fichier = new FileOutputStream("ennemi.sav");

                        ObjectOutputStream oos = new ObjectOutputStream(fichier);
                        
                        System.out.println(e.getDefense());
                        oos.writeInt(e.getDefense());
                        oos.writeInt(e.getVie());
                        oos.writeInt(e.getForce());
                        oos.writeUTF(e.getNom());
                        oos.writeUTF(e.getAttaque());
                        

                        oos.flush();

                        oos.close();
                        fichier.close();
                } catch (java.io.IOException io) {
                        io.printStackTrace();
                        //System.out.println("Ennemi impossible à sauvegarder");
                }
    }

    @Override
    public void sauvegarder(Personnage pers) {
         
                try {
                        oos.writeUTF(pers.getAttaque());
                        oos.writeUTF(pers.getNom());
                        oos.writeInt(pers.getDefense());
                        oos.writeInt(pers.getDefenseEquipement());
                        oos.writeInt(pers.getForce());
                        oos.writeInt(pers.getForceEquipement());
                        oos.writeInt(pers.getVie());
                        oos.writeInt(pers.getViePotion());
                        oos.writeInt(pers.countObservers());
                        oos.flush();
                        oos.close();
                        fichierOUT.close();
                } catch (java.io.IOException e) {
                        e.printStackTrace();
                }


    }

    @Override
    public void sauvegarder(Arme arme) {
            try {
                        FileOutputStream fichier = new FileOutputStream("arme.sav");

                        ObjectOutputStream oos = new ObjectOutputStream(fichier);

                        oos.writeObject(arme);

                        oos.flush();

                        oos.close();
                        fichier.close();
                } catch (java.io.IOException io) {
                        // io.printStackTrace();
                        System.out.println("Arme impossible à sauvegarder");
                }
    }

    @Override
    public void sauvegarder(Armure armure) {
             try {
                        FileOutputStream fichier = new FileOutputStream("armure.sav");

                        ObjectOutputStream oos = new ObjectOutputStream(fichier);

                        oos.writeObject(armure);

                        oos.flush();

                        oos.close();
                        fichier.close();
                } catch (java.io.IOException io) {
                        // io.printStackTrace();
                        System.out.println("Armure impossible à sauvegarder");
                }
    }

    @Override
    public void sauvegarder(Objet objet) {
             try {
                        FileOutputStream fichier = new FileOutputStream("objet.sav");

                        ObjectOutputStream oos = new ObjectOutputStream(fichier);

                        oos.writeObject(objet);

                        oos.flush();

                        oos.close();
                        fichier.close();
                } catch (java.io.IOException io) {
                        // io.printStackTrace();
                        System.out.println("Objet imposible à sauvegarder");
                }
    }


    @Override
    public Ennemi ennemicharger() {
        
                try {
                
                        FileInputStream fichier = new FileInputStream("ennemi.sav");

                        @SuppressWarnings("resource")
                        ObjectInputStream ois = new ObjectInputStream(fichier);
                        return (Ennemi) ois.readObject();

                } catch (java.io.IOException e) {

                        return new Ennemi();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
                return null;

    }

    @Override
    public Personnage personnagecharger() {
            try {
                Personnage p;
                ChargementDynamique personnage = this.chargementChargementDynamique();
                p = Personnage.getInstance();
                p.Personnage(personnage);
                ois.close();
                fichierIN.close();
                return p;
            
              
            } catch (InstantiationException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }

    @Override
    public Arme armecharger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Armure armurecharger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Objet objetcharger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void visiter(String attaque) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public ChargementDynamique chargementChargementDynamique() throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
    
        String tmp;
        tmp = ois.readUTF();
        ChargementDynamique tmpJar = new ChargementDynamique(tmp);
        tmpJar.ChargermentJar();
        return tmpJar;
    }
    
}
