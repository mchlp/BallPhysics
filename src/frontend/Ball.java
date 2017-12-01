/*
 * Michael Pu
 * BallPhysics - Ball
 * November 27, 2017
 */

package frontend;

import backend.Coordinate;
import backend.Sprite;
import backend.Velocity;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;

public class Ball extends Sprite {

    private static final int RADIUS = 20;
    private static final Color FILL = Color.GREEN;

    private Velocity mVelocity;
    private Circle mCircle;
    private PhongMaterial mMaterial;

    public Ball(double x, double y) {
        super(new Circle(RADIUS));
        mPosition = new Coordinate(x, y);
        mCircle = (Circle) mNode;
        mCircle.setFill(FILL);
    }

    @Override
    public void update(double deltaTime) {

        Velocity curVelocity = new Velocity(2, 2, true);

        for (Line line : sLineList) {
            if (Shape.intersect(line.getmNode(), mCircle).getBoundsInLocal().getWidth() != -1) {
                System.out.println("INTERSECT");
                curVelocity.setXY(curVelocity.getXSpeed(), 0);
            }
        }

        mPosition = mPosition.move(curVelocity);

        updateSpritePosition();
    }

    private void updateSpritePosition() {
        getmNode().setCenterX(mPosition.getX());
        getmNode().setCenterY(mPosition.getY());
    }

    public Circle getmNode() {
        return mCircle;
    }
}
