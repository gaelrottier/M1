package fr.miage.m1.tp2.main;

import fr.miage.m1.tp2.creatures.AbstractCreature;
import fr.miage.m1.tp2.creatures.ICreature;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import fr.miage.m1.tp2.creatures.StupidCreature;
import fr.miage.m1.tp2.creatures.visual.CreatureSimulator;
import fr.miage.m1.tp2.creatures.visual.CreatureVisualizer;
import fr.miage.m1.tp2.creatures.CrabeAdapter;
import fr.miage.m1.tp2.util.IColorStrategy;
import fr.miage.m1.tp2.util.MultipleColorStrategy;
import fr.miage.m1.tp2.util.UniqueColorStrategy;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;
import javax.swing.JOptionPane;
import my.company.simulated.Crabe;
import my.company.simulated.Direction;

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
        for (ICreature c : simulator.getCreatures()) {
            ((AbstractCreature) c).addObserver(visualizer);
        }
        simulator.start(false);
    }

    private double random() {
        return Math.random() * 10;
    }

    public void applyStrategy(String option) {
        IColorStrategy strategy = null;

        switch (option) {
            case "Couleur unique":
                strategy = new UniqueColorStrategy(Color.black);
                break;
            case "Couleurs distinctes":
                strategy = new MultipleColorStrategy();
                break;
            default:
                break;
        }

        Random rand = new Random();

        Rectangle r = new Rectangle(new Dimension((int) random(), (int) random()));
        Color col = Color.WHITE;
        for (int i = 0; i < 10000; i++) {
            if (i%2 == 0){
                col = Color.BLACK;
            }   
                simulator.getCreatures().add(new StupidCreature(simulator,
                        new Point2D.Double(random(), random()),
                        random(),
                        random(),
col));
            

            Crabe c = new Crabe((float) random(), (float) random(), (float) random(), Direction.Gauche, (float) simulator.getSize().width, (float) simulator.getSize().height);
            ICreature ca = new CrabeAdapter(c, simulator, col, new Point2D.Double(random(), random()));

            simulator.getCreatures().add(ca);
        }

    }

    public static void main(String args[]) {
        String[] options = {"Couleur unique", "Couleurs distinctes"};
        String chosenOption = (String) JOptionPane.showInputDialog(
                new JFrame(),
                "Choisissez une stratégie à appliquer",
                "Choix de la stratégie",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        Launcher launcher = new Launcher();

        launcher.applyStrategy(chosenOption);

        launcher.init();
        launcher.setVisible(true);
    }

}
