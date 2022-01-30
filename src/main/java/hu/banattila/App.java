package hu.banattila;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private int buttonClick = 0;
    private int totalClick = 0;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        Label label = new Label("Button Clicked: " + buttonClick);
        Label labelTwo = new Label("Total clicked: " + totalClick);
        Button button = new Button("Count");
        button.setOnAction( event -> {
            buttonClick++;
            totalClick++;
            label.setText("Button Clicked: " + buttonClick);
            labelTwo.setText("Total clicked: " + totalClick);
        });
        root.setSpacing(30);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(label, labelTwo, button);

        root.setOnMouseClicked(event -> {
            totalClick++;
            labelTwo.setText(String.valueOf("Total clicked: " + totalClick));
        });
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}