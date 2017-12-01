/*
 * Michael Pu
 * BallPhysics - Game
 * November 27, 2017
 */

package frontend;

import backend.Sprite;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {

    private long prevTime;
    private Line curDrawingLine;

    private Pane root;
    private ArrayList<Sprite> allSpriteList;
    private ArrayList<Line> allLineList;

    private static final double WINDOW_SIZE_TO_SCREEN_RATIO = 0.8;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = primaryScreenBounds.getWidth() * WINDOW_SIZE_TO_SCREEN_RATIO;
        double screenHeight = primaryScreenBounds.getHeight() * WINDOW_SIZE_TO_SCREEN_RATIO;

        allSpriteList = new ArrayList<>();
        allLineList = new ArrayList<>();

        root = new Pane();
        root.setPrefWidth(screenWidth);
        root.setPrefHeight(screenHeight);

        Scene scene = new Scene(root);

        root.setBackground(new Background(new BackgroundFill(Utilities.BACKGROUND_GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setTitle("Ball Physics");
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        Sprite.setPane(root);
        Sprite.setSpriteArrayList(allSpriteList);
        Sprite.setLineList(allLineList);

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent press) {
                switch (press.getButton()) {
                    case SECONDARY:
                        new Ball(press.getX(), press.getY());
                        break;
                }
                press.consume();
            }
        });

        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent drag) {

                switch (drag.getButton()) {
                    case PRIMARY:


                        Point2D dragPoint = new Point2D(drag.getX(), drag.getY());

                        if (curDrawingLine == null) {
                            curDrawingLine = new Line(dragPoint);
                        }

                        double yDiff = drag.getY() - curDrawingLine.getmNode().getStartY();
                        double xDiff = drag.getX() - curDrawingLine.getmNode().getStartX();
                        double angle = Math.abs(Math.toDegrees(Math.atan2(Math.abs(yDiff), Math.abs(xDiff))));

                        if (yDiff < 0) {
                            if (xDiff > 0) {
                                angle += 0;
                            } else {
                                angle = 90 + (90 - angle);
                            }
                        } else {
                            if (xDiff < 0) {
                                angle += 180;
                            } else {
                                angle = 270 + (90 - angle);
                            }
                        }

                        //System.out.println(angle);

                        if (drag.isControlDown()) {
                            if (angle > 45 && angle <= 135 || angle > 225 && angle <= 315) {
                                curDrawingLine.setEnd(new Point2D(curDrawingLine.getmNode().getStartX(), dragPoint.getY()));
                            } else {
                                curDrawingLine.setEnd(new Point2D(dragPoint.getX(), curDrawingLine.getmNode().getStartY()));
                            }

                        } else {
                            curDrawingLine.setEnd(new Point2D(drag.getX(), drag.getY()));
                        }
                        break;
                }

                drag.consume();
            }
        });

        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent release) {

                switch (release.getButton()) {
                    case PRIMARY:
                        curDrawingLine = null;
                        break;
                }
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
