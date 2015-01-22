package fr.miage.m1.tp2.creatures.visual;

import java.awt.Dimension;

import fr.miage.m1.tp2.creatures.ICreature;


public interface IEnvironment {

	public Dimension getSize();

	public Iterable<ICreature> getCreatures();

}
