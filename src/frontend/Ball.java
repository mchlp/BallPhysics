/*
 * Michael Pu
 * BallPhysics - Ball
 * November 27, 2017
 */

package frontend;

import backend.Velocity;
import javafx.geometry.Point2D;

public class Ball {
    private Point2D mPosition;
    private Velocity mVelocity;

    public Ball(double x, double y) {
        mPosition = new Point2D(x, y);
    }
}
