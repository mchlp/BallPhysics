/*
 * Michael Pu
 * BallPhysics - Line
 * November 2017
 */

package frontend;

import backend.Coordinate;

public class Line {

    public static final int LINE_THICKNESS = 3;

    private Coordinate mStart;
    private Coordinate mEnd;

    public Line(Coordinate start) {
        this(start, start);
    }

    public Line(Coordinate start, Coordinate end) {
        mStart = start;
        mEnd = end;
    }

    public void setEnd(Coordinate end) {
        mEnd = end;
    }
}
