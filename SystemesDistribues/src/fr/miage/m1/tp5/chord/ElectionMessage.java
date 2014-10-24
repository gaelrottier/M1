package fr.miage.m1.tp5.chord;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElectionMessage implements Message {

    private Identifier leader;

    public ElectionMessage(Identifier leader) {
        this.leader = leader;
    }

    @Override
    public void handle(Peer peer) {
        try {
            int compare = leader.compareTo(peer.getId());
            String message = "Handling election message on peer with id " + peer.getId() + ", " + leader + ".compareTo(" + peer.getId() + ") = " + compare + ", participate ? ";
            String participate = "false";
            
            if (compare > 0) {
                peer.getSuccessor().receive(this);
            } else if (compare < 0 && !peer.getParticipant()) {
                peer.setParticipant(true);
                participate = "true";
                leader = peer.getId();
                peer.getSuccessor().receive(this);
            } else if (compare < 0 && peer.getParticipant()) {
                participate = "true";
            } else if (compare == 0) {
                leader = peer.getId();
                participate = "true";
                peer.setLeaderId(leader);
            }
            
            System.out.println(message + participate);
        } catch (RemoteException ex) {
            Logger.getLogger(ElectionMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
