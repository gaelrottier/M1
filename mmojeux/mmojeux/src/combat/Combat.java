/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package combat;


import ennemi.Ennemi;
import static ihm.IHM.console;
import java.util.Observable;
import personnage.Personnage;

/**
 *
 * @author Justin
 */
/**  Classe qui s'occupe des combats
*/
public class Combat extends Observable {
    
   int hpEnnemi;
   int hpPersonnage;
   int defEnnemi;
   int defPersonnage;
   int hpPersoInitial;
   int hpEnnemiInitial;
   String gagnant; 
   boolean Potion = false;
   boolean mortPerso = false;
   boolean mortEnnemi = false;
   /** Constructeur des combats
   */
   public Combat(){
        hpPersoInitial = Personnage.getInstance().getVie();
        hpEnnemiInitial = Ennemi.getInstance().getVie();
        hpPersonnage = Personnage.getInstance().getVie();
        hpEnnemi = Ennemi.getInstance().getVie();
        defEnnemi = Ennemi.getInstance().getDefense();
        defPersonnage = Personnage.getInstance().getDefenseEquipement();
        deroulementCombat();
    }
    /** Méthode qui lance un combat 
    */
    public void deroulementCombat(){
        
        console.append("\n"+ "Les adversaires rentre dans l'arène, le combat commence : ");  
        boolean verif = false;
        while(verif != true){
              int PourcentPerso = ((hpPersonnage * 100) / Personnage.getInstance().getVie());
              if((PourcentPerso < 40) && (Potion == false) && (Personnage.getInstance().getViePotion() != 0)){
                  console.append("\n"+ "Le héros lance une potion : "); 
                  hpPersonnage = hpPersonnage + Personnage.getInstance().getViePotion();
                  console.append("\n"+ "Il a maintenant " + hpPersonnage + " HP"); 
                  Potion = true;
                  
              }else{
                    System.out.println(PourcentPerso);
                    console.append("\n" + "Vous lancez votre attaque :");
                    console.append("\n"+ Personnage.getInstance().getAttaque());
                    hpEnnemi = hpEnnemi - Personnage.getInstance().getForce();
                    hpEnnemi = hpEnnemi + defEnnemi;
                    console.append("\n"+"HP ennemi :");
                    String vieRestantPerso = hpEnnemi + " / " + hpEnnemiInitial;
                    console.append("\n" + vieRestantPerso);
              }
              if((hpEnnemi < 0) || (hpEnnemi == 0)){
                  verif = true;
                  gagnant = "Vous avez gagné !!";
              }
              if(verif == false){
                     console.append("\n"+"L'ennemi attaque désormais, il lance son attaque : ");
                     console.append("\n"+Ennemi.getInstance().getAttaque());
                     hpPersonnage = hpPersonnage - Ennemi.getInstance().getForce();
                     hpPersonnage = hpPersonnage + defPersonnage;
                     console.append("\n"+"Vos HP :");
                     String vieRestantEnnemi = hpPersonnage + " / " + hpPersoInitial;
                     console.append("\n"+ vieRestantEnnemi);
                     if((hpPersonnage < 0) ||(hpPersonnage == 0)) {
                        verif = true;
                        gagnant = "Vous avez perdu !!";
                     }
              }
        }
        console.append("\n"+ "Combat terminé");
        console.append("\n"+ gagnant);
    }

}
