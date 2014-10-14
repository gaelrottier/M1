/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Justin
 */

/** Panel pour affecté un fond'écran à l'ihm principal   
 */
public class Background extends JPanel {
        public Background(){
            
   
        }
        public void paintComponent(Graphics g){
        try {
                        Image img = ImageIO.read(new File("ressources\\fond.jpg"));
                        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {                       
                        e.printStackTrace();
                }
         
    }
}