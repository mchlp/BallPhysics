/*
 * Michael Pu
 * BallPhysics - Ball
 * November 27, 2017
 */

package frontend;

import backend.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Sprite implements Collidable {

    private static final int RADIUS = 20;
    private static final Color FILL = Color.GREEN;

    private Circle circle;

    public Ball(double x, double y) {
        super(new Circle(RADIUS));
        position = new Coordinate(x, y);
        circle = (Circle) node;
        circle.setFill(FILL);
    }

    @Override
    public void update(double deltaTime) {

        Velocity curVelocity = new Velocity(0, 2, true);

        double xSpeed = -1;
        for (Collidable collidable : Collidable.collidableList) {
            if (collidable != this) {
                if (collidable instanceof Line) {
                    if (CollisonEngine.circleLine(circle, ((Line) collidable).getNode())) {
                        xSpeed = 0;
                        for (int i = 0; i < RADIUS * 2; i += 5) {
                            Ball leftTest = new Ball(this.circle.getCenterX() - i, this.circle.getCenterY());
                            leftTest.updateSpritePosition();
                            Ball rightTest = new Ball(this.circle.getCenterX() + i, this.circle.getCenterY());
                            rightTest.updateSpritePosition();

                            if (!CollisonEngine.circleLine(leftTest.getNode(), ((Line) collidable).getNode())) {
                                xSpeed = -0.5;
                                break;
                            } else if (!CollisonEngine.circleLine(rightTest.getNode(), ((Line) collidable).getNode())) {
                                xSpeed = 0.5;
                                break;
                            }
                        }
                    }
                } else if (collidable instanceof Ball) {
                    if (CollisonEngine.circleCircle(circle, ((Ball) collidable).circle)) {
                        xSpeed = 0;
                        for (int i = 0; i < 5; i++) {
                            Ball leftTest = new Ball(this.circle.getCenterX() - i, this.circle.getCenterY());
                            leftTest.updateSpritePosition();
                            Ball rightTest = new Ball(this.circle.getCenterX() + i, this.circle.getCenterY());
                            rightTest.updateSpritePosition();

                            if (!CollisonEngine.circleCircle(leftTest.getNode(), ((Ball) collidable).circle)) {
                                xSpeed = -0.5;
                                break;
                            } else if (!CollisonEngine.circleCircle(rightTest.getNode(), ((Ball) collidable).circle)) {
                                xSpeed = 0.5;
                                break;
                            }
                        }
                    }
                }

                if (xSpeed == 0) {
                    break;
                }
            }
        }

        if (xSpeed != -1) {
            curVelocity.setXY(xSpeed, 0);
        }

        position = position.move(curVelocity);
        updateSpritePosition();
    }

    private void updateSpritePosition() {
        getNode().setCenterX(position.getX());
        getNode().setCenterY(position.getY());
    }

    public Circle getNode() {
        return circle;
    }
}
