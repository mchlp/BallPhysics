/*
 * Michael Pu
 * BallPhysics - Ball
 * November 27, 2017
 */

package frontend;

import backend.Coordinate;
import backend.Velocity;

public class Ball {
    private Coordinate mPosition;
    private Velocity mVelocity;

    public Ball(double x, double y) {
        mPosition = new Coordinate(x, y);
    }
}
