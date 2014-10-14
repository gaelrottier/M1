/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personnage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import chargementDynamique.ChargementDynamique;
import serialization.Serialization;
import java.util.Observable;

import serialization.Archive;

/**
 *
 * @author Justin
 */

/** méthode qui s'occupe de la gestion du personnage  
 */
public class Personnage extends Observable{
    private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
    private int vie;
    private int force;
    private int defense;
    private String nomLaClasse; 
    private String nomAttaque;
    private int forceArme;
    private int defenseArmure;
    private int potion; 
    ChargementDynamique personnage;
    private static Personnage pers;

    /** Constructeur personnage  
    */
    private Personnage() {
        this.nomLaClasse = " ";
        this.vie = 0;
        this.force = 0;
        this.defense = 0;
        this.forceArme = 0;
        this.defenseArmure = 0;
    }
    /** Sigleton  
    */
    public static synchronized Personnage getInstance(){
        if (pers  == null) {
                pers = new Personnage();
        }
        return pers;
    }
    /**   
    *@return nom de classe du personnage   
    */
    public String getNom(){
        return this.nomLaClasse; 
    }
    /**   
    *@return vie du personnage   
    */
    public int getVie(){
        return this.vie;
    }
    /**   
    *@return force du personnage   
    */
     public int getForce(){
        return this.force + this.forceArme;
        
    }
     /**   
    *@return defense du personnage   
    */
     public int getDefense(){
        return this.defense + this.defenseArmure;
    } 
     /** Affecter le nom du personnage  
     */
      public void setNom(String nomtemp){
        this.nomLaClasse = nomtemp; 
        this.updateObservateur();
    }
     /** Affecter la vie au personnage  
     */
      public void setVie(int vietemp){
        this.vie = vietemp;
        this.updateObservateur();
    }
      /** Affecter la force au personnage  
     */
     public void setForce(int forcetemp){
        this.force = forcetemp;
        this.updateObservateur();
    }
     /** Affecter le defense du personnage  
     */
     public void setDefense(int defensetemp){
        this.defense = defensetemp;
        this.updateObservateur();
    }
    /** Affecter la force du personnage avec l'arme  
     */
     public void setForceArme(int forceArme){
         System.out.println("Ajout d'une arme avec comme force : " + forceArme);
         this.forceArme = forceArme;
         this.updateObservateur();
     }
     /** Affecter la defense du personnage avec l'armure  
     */
     public void setDefenseArmure(int defenseArmure){
         System.out.println("Ajout d'une armure avec comme défense : " + defenseArmure);
         this.defenseArmure = defenseArmure;
         this.updateObservateur();
     }
     /** Affecter la valeur de la potion  
     */
     public void setViePotion(int viePotion){
         System.out.println("Potion réglé à " + viePotion);
         this.potion = viePotion;
         this.updateObservateur();
     }
     /**  
     *@return la valeur de la potion  
     */
     public int getViePotion(){
         return this.potion;
     }
     /** Affecter le nom de l'attaque  
     */
     public void setAttaque(String attaque){
         this.nomAttaque = attaque;
     }
     /**  
     *@return le nom de l'attaque  
     */
     public String getAttaque(){
         return this.nomAttaque;
     }
     /**  
     *@return la force + la force de l'équipement  
     */
     public int getForceEquipement(){
         return this.force + forceArme;
     }
     /**  
     *@return la defense + la defense de l'équipement  
     */
      public int getDefenseEquipement(){
         return this.defense + defenseArmure;
     }
    /** Constructeur qui permet de charger une classe de personnage  
     */
    public void Personnage(Object item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
       this.personnage = (ChargementDynamique) item;
       
       //this.personnage = perso;
                this.nomLaClasse = (String) this.personnage.getMethodForName("getNom").invoke(this.personnage.getClassInstancie());
                this.vie =(int) this.personnage.getMethodForName("getVie").invoke(this.personnage.getClassInstancie());
                this.force =(int) this.personnage.getMethodForName("getForce").invoke(this.personnage.getClassInstancie());
                this.defense =(int) this.personnage.getMethodForName("getDefense").invoke(this.personnage.getClassInstancie());
                this.nomAttaque = (String) this.personnage.getMethodForName("getAttaque").invoke(this.personnage.getClassInstancie());
                System.out.println("Nom : " + getNom());
                System.out.println("Vie : " + getVie());
                System.out.println("Defense : " + getDefense());
                System.out.println("Force : " + getForce());
                this.updateObservateur();
    }
     /** Ajoute l'observateur  
     */
     public void addObservateur(Observateur obs) {
              this.listObservateur.add(obs);
     }
    /** Supprimer l'observateur   
     */
     public void delObservateur() {
         this.listObservateur = new ArrayList<Observateur>();
     }
   /** Avertit l'observer  
     */
    public void updateObservateur() {
         for(Observateur obs : this.listObservateur )
       obs.update(this.nomLaClasse,this.getVie(),this.getForceEquipement(),this.getDefenseEquipement());
     }
    /** Met à jour les observeurs 
     */
     public void updatePlugginObservateur() {
         for(Observateur obs : this.listObservateur )
        obs.update(this.nomLaClasse,this.vie,this.forceArme,this.defenseArmure);
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
        
        this.vie = ((Personnage)resultat).vie;
        this.force = ((Personnage)resultat).force;
        this.defense = ((Personnage)resultat).defense;
        this.nomLaClasse = ((Personnage)resultat).nomLaClasse;
        this.forceArme = ((Personnage)resultat).forceArme;
        this.defenseArmure = ((Personnage)resultat).defenseArmure;
        this.nomAttaque = ((Personnage)resultat).nomAttaque;
        this.forceArme = ((Personnage)resultat).forceArme;
        this.potion = ((Personnage)resultat).potion;
        
        this.listObservateur = ((Personnage)resultat).listObservateur;
        this.personnage = ((Personnage)resultat).personnage;
        this.pers = ((Personnage)resultat).pers;
              
        return true;
    }
 }
