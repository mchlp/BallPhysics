/*
 * Michael Pu
 * BallPhysics - Sprite
 * November 27, 2017
 */

package backend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class Sprite {

    public static ArrayList<Sprite> spriteList = new ArrayList<>();

    protected Coordinate position;
    protected Node node;

    protected Sprite(Node node) {
        this.node = node;
    }

    public abstract void update(double deltaTime);

    public Node getNode() {
        return node;
    }
}
