package ihm;
import combat.Combat;
import chargementDynamique.GestionPlug;
import ennemi.Ennemi;
import serialization.Serialization;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import personnage.Observateur;
import personnage.Personnage;

/** Classe qui crée l'interface
 */
public class IHM extends JFrame implements Serializable {
	
        public static JComboBox comboClasse = new JComboBox();
        public static JComboBox comboArme = new JComboBox();
        public static JComboBox comboArmure = new JComboBox();
        public static JComboBox comboPotion = new JComboBox();
        public static JTextArea console = new JTextArea(); 
        JLabel personnage = new JLabel("Personnage");
	JLabel classe = new JLabel("Classe :");
	JLabel vie = new JLabel("Vie : ");
	JLabel attaque = new JLabel("Attaque : ");
	JLabel defense = new JLabel("Defense :");
	JLabel personnageinfo = new JLabel("0");
	JLabel classeinfo = new JLabel("Aucune");
	JLabel vieinfo = new JLabel("0");
	JLabel forceinfo = new JLabel("0");
	JLabel defenseinfo = new JLabel("0");
	
	JLabel personnageEnnemie = new JLabel("Ennemi");
	JLabel classeE = new JLabel("Classe :"); 
	JLabel vieE = new JLabel("Vie : ");
	JLabel attaqueE = new JLabel("Attaque : ");
	JLabel defenseE = new JLabel("Defense :");
	JLabel personnageinfoE = new JLabel("0");
	JLabel classeinfoE = new JLabel("Aucune");
	JLabel vieinfoE = new JLabel("0");
	JLabel forceinfoE = new JLabel("0");
	JLabel defenseinfoE = new JLabel("0");
	
	
	JLabel classelabel = new JLabel("Classe :");
	
	JLabel armeLabel = new JLabel("Arme :");
	
	JLabel armureLabel = new JLabel("Armure :");
	
	JLabel potionLabel = new JLabel("Potion :");
	
	JButton play = new JButton("Jouer");

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Fichier");
        private JMenu menuEnnemi = new JMenu("Ennemi");
	private JMenuItem chargementPluggin = new JMenuItem("Chargement pluggin");
        private JMenuItem sauvegarde = new JMenuItem("Sauvegarde");
        private JMenuItem chargement = new JMenuItem("Chargement");
        private JMenuItem gestionEnnemi = new JMenuItem("Gestion ennemi");
        
	private JMenuItem quitter = new JMenuItem("Quitter");
        
        private JScrollPane scroll;
        
	public IHM() throws IOException{
                   scroll=new JScrollPane(console);
               
		   Toolkit tk = Toolkit.getDefaultToolkit();
                   Cursor Curseur;
                   Image img = ImageIO.read(new File("ressources\\curseur.gif"));
                   Curseur = tk.createCustomCursor(img, new Point( 1, 1 ), "Pointeur" );
                   setCursor( Curseur );
                  Background background = new Background();
                   this.setContentPane(background);
                  comboClasse.addItem("Aucun");
                  comboArme.addItem("Aucun");
                  comboArmure.addItem("Aucun");
                  comboPotion.addItem("Aucun");
                  comboClasse.addItemListener(new ClasseState());
                  comboArme.addItemListener(new ArmeState());
                  comboArmure.addItemListener(new ArmureState());
                  comboPotion.addItemListener(new ObjetState());
                  
                  this.setLayout(null); 
		  
		  
		  this.setTitle("RPG");  
		  
		  this.setSize(1000, 600);  
		  this.menu.add(chargementPluggin);
                  this.menu.add(sauvegarde);
                  this.menu.add(chargement);
		  this.menu.add(quitter);
                  this.menuEnnemi.add(gestionEnnemi);
                  
		  this.menuBar.add(menu);
                  this.menuBar.add(menuEnnemi);
		  this.setJMenuBar(menuBar);
                  this.sauvegarde.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                          FileOutputStream fichierOUT;
                           try {
                               fichierOUT = new FileOutputStream("./ennemi.sav");
                               Serialization save;
                              try {
                                  save = new Serialization(fichierOUT);
                                  save.sauvegarder(Ennemi.getInstance());
                                  System.out.println("Sauvegarde OK");
                              } catch (IOException ex) {
                                  Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                              }
					
                           } catch (FileNotFoundException ex) {
                               Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                           }

					
                       }
                   });
                  this.chargement.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           FileInputStream fichierIN;
                           try {
                               fichierIN = new FileInputStream("./ennemi.sav");
                               try {
                                   Serialization load = new Serialization(fichierIN);
                                   load.ennemicharger();
                               } catch (IOException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           } catch (FileNotFoundException ex) {
                               Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
			   
                       }
                   });
                  this.quitter.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                        System.exit(0);
                       }
                   });
                  this.chargementPluggin.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           JFileChooser choix = new JFileChooser();
				int retour = choix.showOpenDialog(null);
				if (retour == JFileChooser.APPROVE_OPTION) {
					// un fichier a été choisi (sortie par OK)
					// nom du fichier choisi
					choix.getSelectedFile().getName();
					// chemin absolu du fichier choisi
					choix.getSelectedFile().getAbsolutePath();

					System.out.println(choix.getSelectedFile()
							.getAbsolutePath());
                               try {
                                   GestionPlug.getInstance().ChargerJar(choix.getSelectedFile()
                                           .getAbsolutePath());

                               } catch (ClassNotFoundException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (InstantiationException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (IllegalAccessException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (IOException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (SecurityException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (NoSuchMethodException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (IllegalArgumentException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (InvocationTargetException ex) {
                                   Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                               }
                         
                                }
                       
                       }
                   });
                  
                  this.gestionEnnemi.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent arg0) {
                      try {
                            console.setText("");
                            IHMEnnemi ihm = new IHMEnnemi();
                      } catch (IOException ex) {
                          Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }        
                 });
		  
                  this.add(personnage);
                  this.personnage.setForeground(Color.white);
		  this.add(classe);
                  this.classe.setForeground(Color.white);
		  this.add(classeinfo);
                  this.classeinfo.setForeground(Color.white);
		  this.add(vie);
                  this.vie.setForeground(Color.white);
		  this.add(vieinfo);
                  this.vieinfo.setForeground(Color.white);
		  this.add(attaque);
                  this.attaque.setForeground(Color.white);
		  this.add(forceinfo);
		  this.forceinfo.setForeground(Color.white);
                  this.add(defense);
                  this.defense.setForeground(Color.white);
		  this.add(defenseinfo);
		  this.defenseinfo.setForeground(Color.white);
		  this.add(personnageEnnemie);
		  this.personnageEnnemie.setForeground(Color.white);
                  this.add(classeE);
                  this.classeE.setForeground(Color.white);
		  this.add(classeinfoE);
                  this.classeinfoE.setForeground(Color.white);
		  this.add(vieE);
                  this.vieE.setForeground(Color.white);
		  this.add(vieinfoE);
                  this.vieinfoE.setForeground(Color.white);
		  this.add(attaqueE);
                  this.attaqueE.setForeground(Color.white);
		  this.add(forceinfoE);
                  this.forceinfoE.setForeground(Color.white);
		  this.add(defenseE);
                  this.defenseE.setForeground(Color.white);
		  this.add(defenseinfoE);
		  this.defenseinfoE.setForeground(Color.white);
		  this.add(classelabel);
                  this.classelabel.setForeground(Color.white);
		  this.add(comboClasse);
                  this.comboClasse.setForeground(Color.white);
		  this.add(armeLabel);
                  this.armeLabel.setForeground(Color.white);
		  this.add(comboArme);
                  this.comboArme.setForeground(Color.white);
		  this.add(armureLabel);
                  this.armureLabel.setForeground(Color.white);
		  this.add(comboArmure);
                  this.comboArmure.setForeground(Color.white);
		  this.add(potionLabel);
                  this.potionLabel.setForeground(Color.white);
		  this.add(comboPotion);
                  this.comboPotion.setForeground(Color.white);
		  this.add(play);
		  this.add(scroll);
                  this.scroll.setBackground(Color.black);
                  this.scroll.setForeground(Color.white);
		  personnage.setBounds(200, 0, 100, 100);
		  classe.setBounds(50,25,200,100);
		  classeinfo.setBounds(50,50,200,100);
		  vie.setBounds(50, 75, 200, 100);
		  vieinfo.setBounds(50,100,200,100);
		  attaque.setBounds(50,125,200,100);
		  forceinfo.setBounds(50,150,200,100);
		  defense.setBounds(50,175,200,100);
		  defenseinfo.setBounds(50,200,200,100);
		 
		  
		  personnageEnnemie.setBounds(580, 0, 100, 100);
		  classeE.setBounds(400,25,200,100);
		  classeinfoE.setBounds(400,50,200,100);
		  vieE.setBounds(400, 75, 200, 100);
		  vieinfoE.setBounds(400,100,200,100);
		  attaqueE.setBounds(400,125,200,100);
		  forceinfoE.setBounds(400,150,200,100);
		  defenseE.setBounds(400,175,200,100);
		  defenseinfoE.setBounds(400,200,200,100);
		  
		  
		  
		  classelabel.setBounds(800, 0, 100, 100);
		  comboClasse.setBounds(800, 70, 150, 40);
		  armeLabel.setBounds(800, 95, 100, 100);
		  comboArme.setBounds(800, 170, 150, 40);
		  armureLabel.setBounds(800, 195, 100, 100);
		  comboArmure.setBounds(800, 270, 150, 40);
		  potionLabel.setBounds(800, 295, 100, 100);
		  comboPotion.setBounds(800, 370, 150, 40);
		  play.setBounds(800,470,150,50);
		  scroll.setBounds(20,375,750,150);
                  this.setResizable(false);
		  this.setLocationRelativeTo(null);
		  this.setVisible(true);
                  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  play.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) { 
			Combat fight = new Combat();
                    } 
                }); 
                  
                  Personnage.getInstance().addObservateur(new Observateur(){
                  

                      @Override
                      public void update(String classe,int vie, int force, int defense) {
                            classeinfo.setText(classe);
                            vieinfo.setText(Integer.toString(vie));
                            forceinfo.setText(Integer.toString(force));
                            defenseinfo.setText(Integer.toString(defense));
                      }
                  });
                 
                 Ennemi.getInstance().addObservateur(new Observateur(){
                      @Override
                      public void update(String classe,int vie, int force, int defense) {
                            classeinfoE.setText(classe);
                            vieinfoE.setText(Integer.toString(vie));
                            forceinfoE.setText(Integer.toString(force));
                            defenseinfoE.setText(Integer.toString(defense));
                      }
                  });
                 
    }
 }
