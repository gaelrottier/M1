package fr.miage.m1.tp1.creatures.visual;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.miage.m1.tp1.visual.Visualizer;
import fr.miage.m1.tp1.creatures.ICreature;

@SuppressWarnings("serial")
public class CreatureVisualizer extends Visualizer {

	private final CreatureSimulator simulator;
	private boolean debug;

	public CreatureVisualizer(CreatureSimulator simulator) {
		this.simulator = simulator;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				handleMousePressed(e);
			}
		});
	}

	protected void handleMousePressed(MouseEvent e) {
		synchronized (simulator) {
			if (simulator.isRunning()) {
				simulator.stop();
			} else {
				simulator.start();
			}
		}
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
		repaint();
	}

	@Override
	protected void paint(Graphics2D g2) {
		if (debug) {
			paintDebuggingFrame(g2);
		}

		super.paint(g2);
	}

	protected void paintDebuggingFrame(Graphics2D g2) {
		// save color
		Color oldColor = g2.getColor();
		g2.setColor(Color.BLACK);

		// draw coordinates
		g2.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0);
		g2.drawLine(0, -getHeight() / 2, 0, getHeight() / 2);

		// restore color
		g2.setColor(oldColor);
	}

	@Override
	protected Iterable<ICreature> getDrawables() {
		return simulator.getCreatures();
	}

}
