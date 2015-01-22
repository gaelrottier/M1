package fr.miage.m1.tp2.creatures;

import java.awt.geom.Point2D;

import fr.miage.m1.tp2.creatures.visual.IEnvironment;

import fr.miage.m1.tp2.simulator.IActionable;
import fr.miage.m1.tp2.visual.IDrawable;

public interface ICreature extends IDrawable, IActionable {

    public IEnvironment getEnvironment();

    public double getSpeed();

    public double getDirection();

    public Point2D getPosition();
}
