package fr.miage.m1.tp3.v3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class Frame {

    JFrame frame;
    private JPanel contentPane;

    void showFrame() {
        if (frame == null) {
            frame = new JFrame("Test menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(new BorderLayout(0, 0));
            frame.setContentPane(contentPane);
        }
        buildMenu();
        frame.setVisible(true);
    }

    @SuppressWarnings("serial")
    void buildMenu() {
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);
        JMenu fileM = new JMenu("Fichier");
        bar.add(fileM);
        fileM.add(new AbstractAction("Save", new ImageIcon("res/save-icon16.png")) {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                System.out.println("DoSaveAction:" + arg0);
            }

            @Override
            public Object getValue(String arg0) {
                if (arg0.equals(Action.ACCELERATOR_KEY)) // cannot be changed later (use putValue when possible - not anonymous)
                {
                    return KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
                }
                return super.getValue(arg0);
            }
        });

        JMenu dynamic = new JMenu("Dynamique");
        bar.add(dynamic);
        Repository<Action> rep = new Repository(new File("F:\\Bibliothèques\\Documents\\classes"), fr.miage.m1.tp3.v3.Action.class);

        for (Class<? extends Action> c : rep.load()) {
            dynamic.add(new AbstractAction(c.getSimpleName()) {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Action réalisée : " + e);
                }
            });
        }
    }

    public static void main(String[] args) {
        new Frame().showFrame();
    }
}
