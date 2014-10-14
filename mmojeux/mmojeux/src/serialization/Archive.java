//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2010_2011 - DUT/S2T
//
// Classe _Archive - Enregistrement et restauration
//
// Edition "Draft"
//
//    + Version 0.0.0	: Cours_2
//
// Auteur : Champoussin Luca
//

package serialization;

import java.io.*;

public abstract class Archive {
   
    // ---                                                      Methode enregistrer

    public static boolean enregistrer (String  radicalFichier, Object obj) {
        String origine;
        String nomFichier;
        FileOutputStream f= null;
        ObjectOutputStream out= null;

        // Construire le nom du fichier de configuration
        //
        nomFichier= radicalFichier + ".data";

        // Construire un fichier logique et le fichier physique associe
        //
                try {f= new FileOutputStream(nomFichier);}
        catch (Exception e) {return false;}

        // Construire un flux de sortie base sur le fichier logique
        //
        try {out= new ObjectOutputStream(f);}
        catch (Exception e) {return false;}

        // Serialiser la configuration dans le flux de sortie
        //
        try{out.writeObject(obj);}
        catch (Exception e) {return false;}

        return true;
      	
   }
  
   // ---                                                      Methode restaurer 
   
   public static Object restaurer (String  radicalFichier) {
        String origine;
        String nomFichier;
        FileInputStream f= null;
        ObjectInputStream in= null;
        Object resultat;

        // Construire le nom du fichier source de la configuration
      	//
     	nomFichier= radicalFichier + ".data";
      
      	// Construire un fichier logique correspondant
      	//
        try {f= new FileInputStream(nomFichier);}
      	catch (Exception e) {return null;}
   	
        // Construire un flux d'entree base sur le fichier logique
      	//
        try {in= new ObjectInputStream(f);}
      	catch (Exception e) {return null;}
   	
        // Acquerir et deserialiser le flux d'entree
      	//
        try{resultat= in.readObject();}
      	catch (Exception e) {return null;}
      	
      	return resultat;
      	
   }

   
}