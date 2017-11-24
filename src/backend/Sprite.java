/*
 * Michael Pu
 * BallPhysics - Sprite
 * November 2017
 */

package backend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Sprite {

    private static Pane mPane;

    protected Coordinate mPosition;
    protected Node mNode;

    protected Sprite(Coordinate position, Node node) {
        mPosition = position;
        mNode = node;
        mPane.getChildren().add(mNode);
    }

    public abstract void update(double deltaTime);

    public Node getmNode() {
        return mNode;
    }

    public static void setPane(Pane pane) {
        mPane = pane;
    }
}
