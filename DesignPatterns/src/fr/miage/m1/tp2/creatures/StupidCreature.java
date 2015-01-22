package fr.miage.m1.tp2.creatures;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Color;
import java.awt.geom.Point2D;

import fr.miage.m1.tp2.creatures.visual.IEnvironment;

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
