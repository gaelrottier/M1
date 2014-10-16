package fr.miage.m1.tp4.chord.exceptions;

import fr.miage.m1.tp4.chord.Identifier;

public class AlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public AlreadyRegisteredException(Identifier id) {
        super("Peer with ID " + id + " already registered");
    }

}
