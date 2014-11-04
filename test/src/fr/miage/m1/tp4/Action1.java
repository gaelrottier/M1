package fr.miage.m1.tp4;

public class Action1 extends Action{
    
    @Menu(name = "Action 1", shortcut = "A")
    public static void afficherData(){
        System.out.println("Ceci est une information; Action 1.");
    }
}
