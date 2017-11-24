/*
 * Michael Pu
 * BallPhysics - Game
 * November 2017
 */

package frontend;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

	private long prevTime;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Ball Physics");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.isControlDown()) {
                    // control is pressed
                } else {
                    // control is not pressed
                }
                click.consume();
            }
        });

		prevTime = System.nanoTime();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long curTime) {
                double deltaTime = (curTime - prevTime) / 1E9;
                System.out.println(1 / deltaTime);
                onUpdate(deltaTime);
                prevTime = curTime;

			}
		};

		timer.start();
	}

	private void onUpdate(double deltaTime) {

	}
}
