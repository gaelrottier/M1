/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import ennemi.Ennemi;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import personnage.Observateur;


/**
 *
 * @author Justin
 */

/** Classe qui crée l'interface de gestion ennemi
 */
public class IHMEnnemi extends JFrame {
     public static JComboBox ennemiCombo = new JComboBox();
 
     public IHMEnnemi() throws IOException{
       
        JLabel ennemi = new JLabel("Ennemi :");
        JLabel classe = new JLabel("Ennemi séléctionné :");
        final JLabel classeInfo = new JLabel("Aucune");
        JLabel vie = new JLabel("Vie :");
        final JLabel vieInfo = new JLabel("0");
        JLabel defense = new JLabel("Defense :");
        final JLabel defenseInfo = new JLabel("0");
        JLabel attaque = new JLabel("Attaque :");
        final JLabel attaqueInfo = new JLabel("0");
     
        Toolkit tk = Toolkit.getDefaultToolkit();
        Cursor Curseur;
        Image img = ImageIO.read(new File("ressources\\curseur.gif"));
        Curseur = tk.createCustomCursor(img, new Point( 1, 1 ), "Pointeur" );
        setCursor( Curseur );
        Background background = new Background();
        this.setContentPane(background);
        this.setLayout(null); 
        ennemi.setBounds(5, 5, 100, 10);
        ennemiCombo.setBounds(5, 20, 275, 50);
        classe.setBounds(72,55,275,50);
        classeInfo.setBounds(5, 70, 275, 50);
        vie.setBounds(5,85 , 275 , 50);
        vieInfo.setBounds(5, 100, 275, 50);
        attaque.setBounds(5, 115, 275, 50);
        attaqueInfo.setBounds(5, 130, 275, 50);
        defense.setBounds(5, 145, 275, 50);
        defenseInfo.setBounds(5, 160, 275, 50);
       
        ennemi.setForeground(Color.white);
        classe.setForeground(Color.white);
        classeInfo.setForeground(Color.white);
        vie.setForeground(Color.white);
        vieInfo.setForeground(Color.white);
        attaque.setForeground(Color.white);
        attaqueInfo.setForeground(Color.white);
        defense.setForeground(Color.white);
        defenseInfo.setForeground(Color.white);
        
        
        this.add(ennemi);
        this.add(ennemiCombo);
        
        ennemiCombo.addActionListener(new ItemAction());
        
        this.add(classe);
        this.add(classeInfo);
        this.add(vie);
        this.add(vieInfo);
        this.add(attaque);
        this.add(attaqueInfo);
        this.add(defense);
        this.add(defenseInfo);
  ;     
        this.setTitle("Ennemi");  
        this.setSize(290, 250);  
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        Ennemi.getInstance().addObservateur(new Observateur(){
                 public void update(String classe,int vie, int force, int defense) {
                            classeInfo.setText(classe);
                            vieInfo.setText(Integer.toString(vie));
                            attaqueInfo.setText(Integer.toString(force));
                            defenseInfo.setText(Integer.toString(defense));
                      }
                  });
                 
    }
    
    
}
