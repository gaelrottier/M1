package fr.miage.m1.tp4;

public class Action1 extends Action{
    
    @Menu(name = "Action 1", shortcut = "A", comportement = "prout")
    public static void afficherData(String a){
        System.out.println("Ceci est une information; Action 1.");
    }
    
    public static void prout(){
        System.out.println("prout Action 1");
    }
}
