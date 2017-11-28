/*
 * Michael Pu
 * BallPhysics - Line
 * November 27, 2017
 */

package frontend;

import backend.Coordinate;
import backend.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Line extends Sprite {

    private static final int LINE_THICKNESS = 10;
    private static final Color LINE_COLOUR = Color.RED;

    private Coordinate mStart;
    private Coordinate mEnd;
    private javafx.scene.shape.Line mLine;

    public Line(Coordinate start) {
        this(start, start);
    }

    public Line(Coordinate start, Coordinate end) {
        super(new javafx.scene.shape.Line());
        mLine = (javafx.scene.shape.Line) mNode;
        mLine.setStroke(LINE_COLOUR);
        mLine.setStrokeWidth(LINE_THICKNESS);
        mLine.setStrokeLineCap(StrokeLineCap.ROUND);
        mStart = start;
        mEnd = end;
        updateSprite();
    }

    public void setEnd(Coordinate end) {
        mEnd = end;
    }

    private void updateSprite() {
        mLine.setStartX(mStart.getX());
        mLine.setStartY(mStart.getY());
        mLine.setEndX(mEnd.getX());
        mLine.setEndY(mEnd.getY());
    }

    @Override
    public void update(double deltaTime) {
        updateSprite();
    }
}
