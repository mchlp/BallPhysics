/*
 * Michael Pu
 * BallPhysics - Coordinate
 * December 01, 2017
 */

package backend;

import javafx.geometry.Point2D;

public class Coordinate extends Point2D {

    public Coordinate(double x, double y) {
        super(x, y);
    }

    public Coordinate move(Velocity velocity) {
        return new Coordinate(getX() + velocity.getXSpeed(), getY() + velocity.getYSpeed());
    }

}
