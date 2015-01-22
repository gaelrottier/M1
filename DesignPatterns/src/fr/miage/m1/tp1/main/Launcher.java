package fr.miage.m1.tp1.main;

import fr.miage.m1.tp1.creatures.AbstractCreature;
import fr.miage.m1.tp1.creatures.ICreature;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import fr.miage.m1.tp1.creatures.StupidCreature;
import fr.miage.m1.tp1.creatures.visual.CreatureSimulator;
import fr.miage.m1.tp1.creatures.visual.CreatureVisualizer;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

@SuppressWarnings("serial")
public class Launcher extends JFrame {

    private final CreatureVisualizer visualizer;
    private final CreatureSimulator simulator;

    public Launcher() {
        simulator = new CreatureSimulator(new Dimension(640, 480));

        setName("Creature Simulator Test");
        setLayout(new BorderLayout());

        visualizer = new CreatureVisualizer(simulator);
        visualizer.setDebug(true);
        visualizer.setPreferredSize(simulator.getSize());
        add(visualizer, BorderLayout.CENTER);
        pack();

        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                simulator.setSize(Launcher.this.getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }

        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                exit(evt);
            }
        });
    }

    private void exit(WindowEvent evt) {
        System.exit(0);
    }

    public void init() {
        Random rand = new Random();
        float r, g, b;

        for (int i = 0; i < rand.nextInt(100); i++) {
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();

            simulator.getCreatures().add(new StupidCreature(simulator, new Point2D.Double(random(), random()), random(), random(), new Color(r, g, b)));
        }

        for (ICreature c : simulator.getCreatures()) {
            ((AbstractCreature) c).addObserver(visualizer);
        }
        simulator.start(false);
    }

    private double random() {
        return Math.random() * 10;
    }

    public static void main(String args[]) {
        Launcher launcher = new Launcher();
        launcher.init();
        launcher.setVisible(true);
    }

}
