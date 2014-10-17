package fr.miage.m1.tp4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Repository<fr.miage.m1.tp4.Action> rep = new Repository(new File("F:\\Bibliothèques\\Documents\\MIAGE M1 AGAIN\\test\\build\\classes"), fr.miage.m1.tp4.Action.class);
        Method[] ms;
        Annotation[] as;

        for (Class<? extends fr.miage.m1.tp4.Action> c : rep.load()) {
            ms = c.getMethods();

            for (Method m : ms) {
                as = m.getAnnotations();

                for (Annotation a : as) {
                    if ("Menu".equals(a.annotationType().getSimpleName())) {
                        final Menu me = (Menu) a;
                        dynamic.add(new AbstractAction(me.name(), new ImageIcon()) {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    if ("".equals(me.comportement())) {
                                        m.invoke(c);
                                    } else {
                                        c.getMethod(me.comportement()).invoke(c);
                                    }
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            @Override
                            public Object getValue(String arg0) {
                                if (arg0.equals(Action.ACCELERATOR_KEY)) // cannot be changed later (use putValue when possible - not anonymous)
                                {
                                    int ke = 0;

                                    switch (me.shortcut()) {
                                        case "A":
                                            ke = KeyEvent.VK_A;
                                            break;
                                        case "Q":
                                            ke = KeyEvent.VK_Q;
                                            break;
                                        case "Z":
                                            ke = KeyEvent.VK_Z;
                                            break;
                                        default:
                                            break;
                                    }

                                    return KeyStroke.getKeyStroke(ke, InputEvent.CTRL_DOWN_MASK);
                                }
                                return super.getValue(arg0);
                            }
                        });
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Frame().showFrame();
    }
}
