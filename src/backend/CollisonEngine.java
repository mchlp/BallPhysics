/*
 * Michael Pu
 * BallPhysics - CollisonEngine
 * July 14, 2019
 */

package backend;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class CollisonEngine {

    public static boolean circleLine(Circle circle, Line line) {
        double lineMinX = Math.min(line.getStartX(), line.getEndX());
        double lineMaxX = Math.max(line.getStartX(), line.getEndX());
        double lineMinY = Math.min(line.getStartY(), line.getEndY());
        double lineMaxY = Math.max(line.getStartY(), line.getEndY());
        if (circle.getCenterX() + circle.getRadius() >= lineMinX && circle.getCenterX() - circle.getRadius() <= lineMaxX
                && circle.getCenterY() + circle.getRadius() >= lineMinY && circle.getCenterY() - circle.getRadius() <= lineMaxY) {
            double slope = (line.getEndY() - line.getStartY()) / (line.getEndX() - line.getStartX());
            double yInt = line.getEndY() - (slope * line.getEndX());
            double perpSlope = -1 / slope;
            double perpYInt = circle.getCenterY() - (perpSlope * circle.getCenterX());
            double intX = (yInt - perpYInt) / (perpSlope - slope);
            double intY = (slope * intX) + yInt;
            double dis = getDistance(circle.getCenterX(), circle.getCenterY(), intX, intY);
            return dis <= (line.getStrokeWidth() / 2) + circle.getRadius();
        }
        return false;
    }

    public static boolean circleCircle(Circle circle1, Circle circle2) {
        return getDistance(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), circle2.getCenterY()) <= circle1.getRadius() + circle2.getRadius();
    }

    private static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
