package fr.miage.m1.tp1.creatures.visual;


import java.awt.Dimension;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.miage.m1.tp1.simulator.Simulator;

import fr.miage.m1.tp1.creatures.ICreature;


/**
 * Environment for the creatures together with the visualization facility.
 */
public class CreatureSimulator extends Simulator<ICreature> implements IEnvironment {
	
	private Dimension size;

	public CreatureSimulator(Dimension initialSize) {
		super(new CopyOnWriteArrayList<ICreature>(), 10);
		this.size = initialSize;
	}
	
	/**
	 * @return a copy of current size
	 */
	public synchronized Dimension getSize() {
		return new Dimension(size);
	}
	
	public synchronized void setSize(Dimension size) {
		this.size = size;
	}
	
	/**
	 * @return a copy of the current creature list.
	 */
	@Override
	public Collection<ICreature> getCreatures() {
		return actionables;
	}

}
