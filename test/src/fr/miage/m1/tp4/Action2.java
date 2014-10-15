package fr.miage.m1.tp4;

public class Action2 extends Action {

    @Menu(name = "Action 2", shortcut = "Q")
    public static void afficherData() {
        System.out.println("Ceci est une information; Action 2.");
    }
}
