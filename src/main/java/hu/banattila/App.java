package hu.banattila;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    private final int width = 300;
    private final int height = 300;
    private final double speed = 0.1;
    private final boolean canRepeatVertices = true;
    private final int numOfVertices = 3;
    private final int padding = 20;
    private final double radius = 1.0;
    private final double divDistances = 0.5;
    private final List<Point2D> vertices = new ArrayList<>();
    private Point2D last;
    private Point2D lastChoosenVertex = null;
    private final Group group = new Group();

    @Override
    public void init() throws Exception{
        super.init();
        double degreeStep = 360 / numOfVertices;
        double actDegree = 270;
        double actX = width / 2;
        double actY = padding;

        for (int i = 0; i < numOfVertices; ++i){
            actX = Math.cos(actDegree * Math.PI / 180) * (width / 2 - padding) + width / 2;
            actY = Math.sin(actDegree * Math.PI / 180) * (height / 2 - padding) + height /2;
            Point2D p = new Point2D(actX, actY);
            vertices.add(p);
            actDegree += degreeStep;
        }
        last = new Point2D(Math.random() * width, Math.random() * height);

    }

    private double distance(Point2D p1, Point2D p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    private Point2D halfPoint(Point2D p1, Point2D p2){
        return new Point2D((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }

    private Point2D newPoint(Point2D p1, Point2D p2){
        return new Point2D((p1.getX() + p2.getX()) / (1 / divDistances), (p1.getY() + p2.getY()) / (1 / divDistances));
    }

    private void iterate(){
        int random;
        Point2D chooseOne;

        if (canRepeatVertices){
            random = (int)(Math.random() * numOfVertices);
            chooseOne = vertices.get(random);
        } else {
            do {
                random = (int)(Math.random() * numOfVertices);
                chooseOne = vertices.get(random);
            }while (chooseOne == lastChoosenVertex);
        }

        Point2D halfPoint = halfPoint(chooseOne, last);
        group.getChildren().add(new Circle(halfPoint.getX(), halfPoint.getY(), radius));
        last = halfPoint;
        lastChoosenVertex = chooseOne;
    }

    @Override
    public void start(Stage stage) {

        for (Point2D p: vertices){
            Circle circle = new Circle();
            circle.setCenterX(p.getX());
            circle.setCenterY(p.getY());
            circle.setRadius(radius);
            group.getChildren().add(circle);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), actionEvent -> iterate()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(group, width, height);
        stage.setScene(scene);
        stage.setTitle("Chaos Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}