/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chargementDynamique;

import ennemi.Ennemi;
import static ihm.IHMEnnemi.ennemiCombo;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import personnage.Arme;
import personnage.Armure;
import personnage.Objet;
import personnage.Personnage;

/** Classe qui gère les pluggins et qui envoi à la classe correspondante aux item  
*/
public class GestionPlug{
        /** ArrayList qui stocke chaque pluggin en fonction du type pluggin
        */
        public static ArrayList classe = new ArrayList();
        public static ArrayList arme = new ArrayList();
        public static ArrayList armure = new ArrayList();
        public static ArrayList item = new ArrayList();
        public static ArrayList ennemi = new ArrayList();
        
        
        /** Singleton 
        */
        private static GestionPlug instance;
        
        private GestionPlug(){
            
        }
        public static GestionPlug getInstance()
                        throws InstantiationException, IllegalAccessException,
                        ClassNotFoundException, IOException {
                if (null == instance) { // Premier appel
                        synchronized (GestionPlug.class) {
                                if (null == instance) {
                                        instance = new GestionPlug();
                                }
                        }
                }
                return instance;
        }
        /** Méthode qui test quel type de pluggin à été chargé pour ensuite l'envoyait à l'item correspondant
        */
        private void ajoutpluggin(String chemin) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, MalformedURLException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
            ChargerJar(chemin);
        }
         public void ChargerJar(String chemin) throws ClassNotFoundException,
                        InstantiationException, IllegalAccessException,
                        MalformedURLException, IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
                ChargementDynamique plug = new ChargementDynamique(chemin);
                String name = plug.getNamePluggin(); 
                boolean testIfValide = plug.ChargermentJar();

                if (testIfValide && this.estUneClasse(plug)) {
                            System.out.println("Nom pluggin : " + name.toString());
                            classe.add(plug);
                            System.out.println("Ajout classe");
                            ihm.IHM.comboClasse.addItem(plug);
               
                } else if (testIfValide && this.estUneArme(plug)) {
                            System.out.println("Nom pluggin : " + name.toString());
                            arme.add(plug);
                            System.out.println("Ajout Arme");
                            ihm.IHM.comboArme.addItem(plug);
                      

                } else if (testIfValide && this.estUneArmure(plug)) {
                            System.out.println("Nom pluggin : " + name.toString());
                            armure.add(plug);
                            System.out.println("Ajout Armure");
                            ihm.IHM.comboArmure.addItem(plug);
                        

                }else if (testIfValide && this.estUnObjet(plug)) {
                            System.out.println("Nom pluggin : " + name.toString());
                            item.add(plug);
                            System.out.println("Ajout Objet");
                            ihm.IHM.comboPotion.addItem(plug);
                        

                }else if (testIfValide && this.estUnEnnemi(plug)) {
                            System.out.println("Nom pluggin : " + name.toString());
                            ennemi.add(plug);
                            System.out.println("Ajout ennemi");
                            ennemiCombo.addItem(plug);
                }
                

         }
         /** Fonction qui contrôle si c'est le plugin est une arme grâce aux annotations  
         * @return boolean qui dit oui si oui ou non le pluggin est un item arme
         */
          public Boolean estUneArme(ChargementDynamique cd) {
                // TODO Definir Grace au annotation si c'est un Plugin item

                Annotation[] anno = cd.classCharged.getAnnotations();
                for (int i = 0; i < anno.length; i++) {
                        if (anno[i].toString().contains("Arme")) {
                                return true;
                        }
                }

                return false;
        }

       /** Fonction qui contrôle si c'est le plugin est une classe grâce aux annotations  
         * @return boolean qui dit oui si oui ou non le pluggin est un item classe
         */
        public Boolean estUneClasse(ChargementDynamique cd) {
                // TODO Definir Grace au annotation si c'est un Plugin Classe de
                // personnage
                Annotation[] anno = cd.classCharged.getAnnotations();
                

                for (int i = 0; i < anno.length; i++) {
                    System.out.println(anno[i].toString());
                        if (anno[i].toString().contains("Classe")) {
                                return true;
                        }
                }

                return false;

        }
        /** Fonction qui contrôle si c'est le plugin est une armure grâce aux annotations  
         * @return boolean qui dit oui si oui ou non le pluggin est un item armure
         */
         public Boolean estUneArmure(ChargementDynamique cd) {
                // TODO Definir Grace au annotation si c'est un Plugin Classe de
                // personnage
                Annotation[] anno = cd.classCharged.getAnnotations();

                for (int i = 0; i < anno.length; i++) {
                        if (anno[i].toString().contains("Armure")) {
                                return true;
                        }
                }

                return false;

        }
        /** Fonction qui contrôle si c'est le plugin est un objet grâce aux annotations  
         * @return boolean qui dit oui si oui ou non le pluggin est un item objet
         */
        public Boolean estUnObjet(ChargementDynamique cd) {
                // TODO Definir Grace au annotation si c'est un Plugin Classe de
                // personnage
                Annotation[] anno = cd.classCharged.getAnnotations();

                for (int i = 0; i < anno.length; i++) {
                        if (anno[i].toString().contains("Item")) {
                                return true;
                        }
                }

                return false;

        }
        /** Fonction qui contrôle si c'est le plugin est un ennemi grâce aux annotations  
         * @return boolean qui dit oui si oui ou non le pluggin est un ennemi
         */
          public Boolean estUnEnnemi(ChargementDynamique cd) {
                // TODO Definir Grace au annotation si c'est un Plugin Classe de
                // personnage
                Annotation[] anno = cd.classCharged.getAnnotations();
                for (int i = 0; i < anno.length; i++) {
                        System.out.println(anno[i].toString());
                        if (anno[i].toString().contains("ennem")) {
                                return true;
                        }
                }

                return false;

        }
        /**  Compare la combobox et de la classe IHM et du arraylist de classe et si une correspondance est trouvé charge la classe séléctionné dans le combobox
         */
        public ChargementDynamique chercheClasse(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            ChargementDynamique temp = null;
            
            for(int i = 0; i < classe.size() ; i++){
                System.out.println(classe.get(i));
                String str = obj.toString();
                 if(classe.get(i).toString().equalsIgnoreCase(str)){
                    System.out.println("trouvé");
                    temp = (ChargementDynamique) classe.get(i);
                    Personnage.getInstance().Personnage(temp);
                }
            }
            return temp;
            
        }
        /**  Compare la combobox et de la classe IHM et du arraylist d'arme et si une correspondance est trouvé charge l'arme séléctionné dans le combobox
         */
        public ChargementDynamique chercheArme(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            ChargementDynamique temp = null;
            
            for(int i = 0; i < arme.size() ; i++){
                System.out.println(arme.get(i));
                String str = obj.toString();
                System.out.println("Chaine" + str);
                 if(arme.get(i).toString().equalsIgnoreCase(str)){
                    System.out.println("trouvé");
                    temp = (ChargementDynamique) arme.get(i);
                    Arme.getInstance().Arme(temp);
                }
            }
            return temp;
            
        }
        /**  Compare la combobox et de la classe IHM et du arraylist d'armure et si une correspondance est trouvé charge l'armure séléctionné dans le combobox
         */
        public ChargementDynamique chercheArmure(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            ChargementDynamique temp = null;
            
            for(int i = 0; i < armure.size() ; i++){
                System.out.println(armure.get(i));
                String str = obj.toString();
                 if(armure.get(i).toString().equalsIgnoreCase(str)){
                    System.out.println("trouvé");
                    System.out.println(armure.get(i));
                    temp = (ChargementDynamique) armure.get(i);
                    Armure.getInstance().Armure(temp);
                }
            }
            return temp;
            
        }
        /**  Compare la combobox et de la classe IHM et du arraylist d'ennemi et si une correspondance est trouvé charge l'ennemi séléctionné dans le combobox
         */
        public ChargementDynamique chercheEnnemi(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            ChargementDynamique temp = null;
            for(int i = 0; i < ennemi.size() ; i++){
                System.out.println(ennemi.get(i));
                String str = obj.toString();
                 if(ennemi.get(i).toString().equalsIgnoreCase(str)){
                    System.out.println("ennemi trouvé");
                    System.out.println(ennemi.get(i));
                    temp = (ChargementDynamique) ennemi.get(i);
                    Ennemi.getInstance().Ennemi(temp);
                }
            }
            return temp;
            
        }
        /**  Compare la combobox et de la classe IHM et du arraylist d'item et si une correspondance est trouvé charge l'item séléctionné dans le combobox
         */
        public ChargementDynamique chercheObjet(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            ChargementDynamique temp = null;
            
            for(int i = 0; i < item.size() ; i++){
                System.out.println(item.get(i));
                String str = obj.toString();
                 if(item.get(i).toString().equalsIgnoreCase(str)){
                    System.out.println("trouvé");
                    temp = (ChargementDynamique) item.get(i);
                    Objet.getInstance().Objet(temp);
                }
            }
            return temp;
            
        }
 }
