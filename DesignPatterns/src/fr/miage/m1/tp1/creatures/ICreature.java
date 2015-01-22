package fr.miage.m1.tp1.creatures;

import java.awt.geom.Point2D;

import fr.miage.m1.tp1.creatures.visual.IEnvironment;

import fr.miage.m1.tp1.simulator.IActionable;
import fr.miage.m1.tp1.visual.IDrawable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;

public interface ICreature extends IDrawable, IActionable {

    public IEnvironment getEnvironment();

    public double getSpeed();

    public double getDirection();

    public Point2D getPosition();
}
