/*
 * Michael Pu
 * BallPhysics - Game
 * November 2017
 */

package frontend;

import backend.Coordinate;
import backend.Sprite;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {

	private long prevTime;
    private Line curDrawingLine;

    private Pane root;
    private ArrayList<Sprite> allSpriteList;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	    allSpriteList = new ArrayList<>();

        root = new Pane();
		Scene scene = new Scene(root);

		root.setBackground(new Background(new BackgroundFill(Utilities.BACKGROUND_GREY, CornerRadii.EMPTY, Insets.EMPTY)));

		primaryStage.setTitle("Ball Physics");
		//primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();

        Sprite.setPane(root);
        Sprite.setSpriteArrayList(allSpriteList);

        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent drag) {
                if (curDrawingLine == null) {
                    curDrawingLine = new Line(new Coordinate(drag.getX(), drag.getY()));
                }
                if (drag.isControlDown()) {
                    curDrawingLine.setEnd(new Coordinate(drag.getX(), ((javafx.scene.shape.Line) (curDrawingLine.getmNode())).getStartY()));
                } else {
                    curDrawingLine.setEnd(new Coordinate(drag.getX(), drag.getY()));
                }
                drag.consume();
            }
        });

        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent release) {
                curDrawingLine = null;
                release.consume();
            }
        });

		prevTime = System.nanoTime();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long curTime) {
                double deltaTime = (curTime - prevTime) / 1E9;
                //System.out.println(1 / deltaTime);
                onUpdate(deltaTime);
                prevTime = curTime;
			}
		};

		timer.start();
	}

	private void onUpdate(double deltaTime) {
        for (Sprite sprite : allSpriteList) {
            sprite.update(deltaTime);
        }
	}
}
