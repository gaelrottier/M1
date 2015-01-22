package fr.miage.m1.tp2.creatures;

import fr.miage.m1.tp2.creatures.visual.IEnvironment;
import java.awt.Color;
import java.awt.geom.Point2D;
import my.company.simulated.Crabe;
import my.company.simulated.Direction;
import static java.lang.Math.*;

public class CrabeAdapter extends AbstractCreature {

    private Crabe c;
    private Color color;

    public CrabeAdapter(Crabe c, IEnvironment env, Color color, Point2D position) {
        super(env, position);
//        fieldOfView = PI;

        this.c = c;
        this.color = color;
    }

    @Override
    public IEnvironment getEnvironment() {
        return this.environment;
    }

    @Override
    public double getSpeed() {
        return c.getSpeed();
    }

    @Override
    public double getDirection() {
        Direction d = c.getDirection();
        double dir;

        if (d.equals(Direction.Gauche)) {
            dir = PI;
        } else {
            dir = 0;
        }

        return direction;
    }

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(c.getX(), c.getY());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void act() {
        c.move();

        setPosition(c.getX(), c.getY());
//        move(c.getX(), c.getY());
        notifyObservers();

    }
}
