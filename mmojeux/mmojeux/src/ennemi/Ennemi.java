/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ennemi;

import chargementDynamique.ChargementDynamique;
import serialization.Serialization;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import personnage.Observateur;
import personnage.Personnage;
import serialization.Archive;

/**
 *
 * @author Justin
 */

/**  Classe qui s'occupe de la gestion ennemi
 */
public class Ennemi extends Observable implements Serializable{
    private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
    private int vie;
    private int force;
    private int defense;
    private String nomLaClasse; 
    private String nomAttaque;
    ChargementDynamique personnage;
   
    String nomClass;
   
    private static Ennemi ennemi;
    /**  Constructeur et initizialiseur d'ennemi
    */
    public Ennemi() {
        this.nomLaClasse = " ";
        this.vie = 0;
        this.force = 0;
        this.defense = 0;
    }
   /** Mise en place du sigleton 
   */ 
    public static synchronized Ennemi getInstance(){
        if (ennemi  == null) {
            ennemi = new Ennemi();
        }
        return ennemi;
    }
    /** 
    *@return nom de l'ennemi  
    */
    public String getNom(){
        return this.nomLaClasse; 
    }
     /** 
    *@return vie de l'ennemi  
    */
    public int getVie(){
        return this.vie;
    }
     /** 
    *@return force de l'ennemi  
    */
     public int getForce(){
        return this.force;
        
    }
     /** 
    *@return force de l'ennemi  
    */
     public int getDefense(){
        return this.defense;
    } 
     /** 
    * Methode pour affecté nom à l'ennemi  
    */
      public void setNom(String nomtemp){
        this.nomLaClasse = nomtemp; 
    }
   /** 
    * Methode pour affecté vie à l'ennemi  
    */
    public void setVie(int vietemp){
        this.vie = vietemp;
    }
     /** 
    * Methode pour affecté force à l'ennemi  
    */
     public void setForce(int forcetemp){
        this.force = force;
    }
      /** 
    * Methode pour affecté defense à l'ennemi  
    */
     public void setDefense(int defensetemp){
        this.defense = defensetemp;
    }
      /** 
    * Methode pour affecté nom de l'attaque à l'ennemi  
    */
   
     public void setAttaque(String attaque){
         this.nomAttaque = attaque;
     }
    /** 
    *@return nom de l'attaque 
    */
     public String getAttaque(){
         return this.nomAttaque;
     }
    /** 
    ** Charge un pluggin Ennemi et l'affecte   
    *@return Ennemi 
    */
   
    public void Ennemi(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
       this.personnage = (ChargementDynamique) item;
       
       //this.personnage = perso;
                this.nomLaClasse = (String) this.personnage.getMethodForName("getNom").invoke(this.personnage.getClassInstancie());
                this.vie =(int) this.personnage.getMethodForName("getVie").invoke(this.personnage.getClassInstancie());
                this.force =(int) this.personnage.getMethodForName("getForce").invoke(this.personnage.getClassInstancie());
                this.defense =(int) this.personnage.getMethodForName("getDefense").invoke(this.personnage.getClassInstancie());
                this.nomAttaque =(String) this.personnage.getMethodForName("getAttaque").invoke(this.personnage.getClassInstancie());
                System.out.println("Nom : " + getNom());
                System.out.println("Vie : " + getVie());
                System.out.println("Defense : " + getDefense());
                System.out.println("Force : " + getForce());
                System.out.println("Nom de l'attaque : " + getAttaque());
                this.updateObservateur();
    }
    
    //Ajoute un observateur à la liste
    public void addObservateur(Observateur obs) {
        this.listObservateur.add(obs);
    }
    
    //Retire tous les observateurs de la liste
    public void delObservateur() {
        this.listObservateur = new ArrayList<Observateur>();
    }
  
    //Avertit les observateurs que l'objet observable a changé 
    //et invoque la méthode update() de chaque observateur
    public void updateObservateur() {
        for(Observateur obs : this.listObservateur )
        obs.update(this.nomLaClasse,this.vie,this.force,this.defense);
     }
    
     public void accept(Serialization ser){
        ser.sauvegarder(this);
     }
     
     // ---                                                        Methode enregistrer
    public boolean enregistrer (String  radicalFichier) {
         return Archive.enregistrer(radicalFichier, this);
    }

    // ---                                                        Methode restaurer
    public boolean restaurer (String radicalFichier) {
        Object resultat;

        resultat = Archive.restaurer(radicalFichier);
        
        this.vie = ((Ennemi)resultat).vie;
        this.force = ((Ennemi)resultat).force;
        this.defense = ((Ennemi)resultat).defense;
        this.nomLaClasse = ((Ennemi)resultat).nomLaClasse;
        this.nomAttaque = ((Ennemi)resultat).nomAttaque;
        
        this.listObservateur = ((Ennemi)resultat).listObservateur;
        this.personnage = ((Ennemi)resultat).personnage;
        this.ennemi = ((Ennemi)resultat).ennemi;
              
        return true;
    }
}
