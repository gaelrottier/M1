package fr.miage.m1.tp1.creatures;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Color;
import java.awt.geom.Point2D;

import fr.miage.m1.tp1.creatures.visual.IEnvironment;
import java.util.Observer;
import javafx.beans.InvalidationListener;

/**
 * Sample creature
 */
public class StupidCreature extends AbstractCreature {

    public StupidCreature(IEnvironment environment, Point2D position,
            double direction, double speed, Color color) {
        super(environment, position);

        this.direction = direction;
        this.speed = speed;
        this.color = color;
    }

    public void act() {
        double incX = speed * cos(direction);
        double incY = speed * sin(direction);

        move(incX, incY);
    }
}
