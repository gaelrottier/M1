package fr.miage.m1.tp2.creatures;

import static java.lang.Math.PI;
import static java.lang.Math.toDegrees;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import fr.miage.m1.tp2.creatures.visual.IEnvironment;
import java.util.Observable;

public abstract class AbstractCreature extends Observable implements ICreature {

    public static final int DEFAULT_SIZE = 80;
    public static final int DEFAULT_VISION_DISTANCE = 50;

    /**
     * The field of view (FOV) is the extent of the observable world that is
     * seen at any given moment by a creature in radians.
     */
    protected double fieldOfView = (PI / 4);

    /**
     * The distance indicating how far a creature see in front of itself in
     * pixels.
     */
    protected double visionDistance = DEFAULT_VISION_DISTANCE;

    /**
     * Position
     */
    protected Point2D position;

    /**
     * Speed in pixels
     */
    protected double speed;

    /**
     * Direction in radians (0,2*pi)
     */
    protected double direction;

    /**
     * Color of the creature
     */
    protected Color color;

    /**
     * Reference to the environment
     */
    protected final IEnvironment environment;

    /**
     * Size of the creature in pixels
     */
    protected final int size = DEFAULT_SIZE;

    public AbstractCreature(IEnvironment environment, Point2D position) {
        this.environment = environment;

        setPosition(position);
    }

    // ----------------------------------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------------------------------
    @Override
    public IEnvironment getEnvironment() {
        return environment;
    }

    public double getFieldOfView() {
        return fieldOfView;
    }

    public double getLengthOfView() {
        return visionDistance;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction % (PI * 2);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    public void setPosition(Point2D position) {
        setPosition(position.getX(), position.getY());
    }

    public void setPosition(double x, double y) {
        Dimension s = environment.getSize();

        if (x > s.getWidth() / 2) {
            x = -s.getWidth() / 2;
        } else if (x < -s.getWidth() / 2) {
            x = s.getWidth() / 2;
        }

        if (y > s.getHeight() / 2) {
            y = -s.getHeight() / 2;
        } else if (y < -s.getHeight() / 2) {
            y = s.getHeight() / 2;
        }

        this.position = new Point2D.Double(x, y);
    }

    // ----------------------------------------------------------------------------
    // Positioning methods
    // ----------------------------------------------------------------------------
    protected void move(double incX, double incY) {
        setPosition(position.getX() + incX, position.getY() + incY);
        notifier();
    }

    private void notifier() {
        setChanged();
        notifyObservers(this);
    }

    protected void rotate(double angle) {
        this.direction += angle;
    }

    // ----------------------------------------------------------------------------
    // Painting
    // ----------------------------------------------------------------------------
    @Override
    public void paint(Graphics2D g2) {
        // center the point
        g2.translate(position.getX(), position.getY());
        // center the surrounding rectangle
        g2.translate(-size / 2, -size / 2);
        // center the arc
        // rotate towards the direction of our vector
        g2.rotate(-direction, size / 2, size / 2);

        // useful for debugging
        // g2.drawRect(0, 0, size, size);
        // set the color
        g2.setColor(color);
        // we need to do PI - FOV since we want to mirror the arc
        g2.fillArc(0, 0, size, size, (int) toDegrees(-fieldOfView / 2),
                (int) toDegrees(fieldOfView));

    }

    // ----------------------------------------------------------------------------
    // Description
    // ----------------------------------------------------------------------------
    public String toString() {
        return this.getClass().getName() + " : " + this.getPosition();
    }
}
