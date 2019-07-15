/*
 * Michael Pu
 * BallPhysics - Line
 * November 27, 2017
 */

package frontend;

import backend.Collidable;
import backend.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.ArrayList;

public class Line extends Sprite implements Collidable {

    private static final int LINE_THICKNESS = 10;
    private static final Color LINE_COLOUR = Color.RED;

    public static ArrayList<Line> lineList = new ArrayList<>();

    private Point2D start;
    private Point2D end;
    private javafx.scene.shape.Line line;

    public Line(Point2D start) {
        this(start, start);
    }

    public Line(Point2D start, Point2D end) {
        super(new javafx.scene.shape.Line());
        lineList.add(this);
        line = (javafx.scene.shape.Line) node;
        line.setStroke(LINE_COLOUR);
        line.setStrokeWidth(LINE_THICKNESS);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        this.start = start;
        this.end = end;
        updateSprite();
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    private void updateSprite() {
        line.setStartX(start.getX());
        line.setStartY(start.getY());
        line.setEndX(end.getX());
        line.setEndY(end.getY());
    }

    @Override
    public void update(double deltaTime) {
        updateSprite();
    }

    public javafx.scene.shape.Line getNode() {
        return line;
    }
}
