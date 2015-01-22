package fr.miage.m1.tp1.visual;

import fr.miage.m1.tp1.creatures.AbstractCreature;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Visualizer extends JPanel implements Observer{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // save transformation
        AffineTransform pT = g2.getTransform();
        g2.transform(getTransform());

        paint(g2);

        // restore transformation
        g2.setTransform(pT);
        
        /**
         * Solution à la question 2, mais mange toute la ram
         */
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Visualizer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    Visualizer.this.repaint();
//                }
//            }
//        };
//        t.start();
    }

    protected void paint(Graphics2D g2) {
        // draw all creatures
        for (IDrawable d : getDrawables()) {
            // save transformation for each drawable
            AffineTransform cT = g2.getTransform();

            d.paint(g2);

            // restore transformation
            g2.setTransform(cT);
        }

    }
    
    protected AffineTransform getTransform() {
        return AffineTransform.getTranslateInstance(getWidth() / 2.0, getHeight() / 2.0);
    }

    protected abstract Iterable<? extends IDrawable> getDrawables();

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

}
