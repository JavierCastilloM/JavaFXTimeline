/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxtimeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author JavierCastilloM
 */
public class JavaFXTimeline extends Application {

    public static double ballSpeedX = 1;
    public static double ballSpeedY = 1;

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label();
        Circle ball = new Circle(20, Color.BLACK);
        label.setTranslateX(10);
        label.setTranslateY(10);

        Group pane = new Group();
        pane.getChildren().addAll(label, ball);

        Scene scene = new Scene(pane, 500, 400);

        ball.setTranslateX(scene.getWidth() / 2);
        ball.setTranslateY(scene.getHeight() / 2);

        EventHandler<ActionEvent> eH = e -> {
            PerformanceTracker perfTracker
                    = PerformanceTracker.getSceneTracker(scene);
            label.setText("FPS (Timeline) = " + perfTracker.getInstantFPS());
            if (ball.getTranslateX() < 20) {
                ballSpeedX = Math.random() * 4 + 1;
            } else if (ball.getTranslateX() > scene.getWidth() - 20) {
                ballSpeedX = -(Math.random() * 4 + 1);
            }

            if (ball.getTranslateY() < 47) {
                ballSpeedY = Math.random() * 4 + 1;
            } else if (ball.getTranslateY() > scene.getHeight() - 20) {
                ballSpeedY = -(Math.random() * 4 + 1);
            }

            ball.setTranslateX(ball.getTranslateX() + ballSpeedX);

            ball.setTranslateY(ball.getTranslateY() + ballSpeedY);

        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), eH));

        animation.setCycleCount(Timeline.INDEFINITE);

        animation.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
