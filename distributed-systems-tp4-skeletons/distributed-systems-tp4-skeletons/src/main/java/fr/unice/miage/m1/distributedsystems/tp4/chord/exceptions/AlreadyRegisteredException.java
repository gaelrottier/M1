package fr.unice.miage.m1.distributedsystems.tp4.chord.exceptions;

import fr.unice.miage.m1.distributedsystems.tp4.chord.Identifier;

public class AlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public AlreadyRegisteredException(Identifier id) {
        super("Peer with ID " + id + " already registered");
    }

}
