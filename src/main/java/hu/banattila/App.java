package hu.banattila;

import javafx.application.Application;
import javafx.geometry.Insets;
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

    @Override
    public void start(Stage stage) {

        HBox root = new HBox();
        Label label = new Label("Counter");
        TextField tf = new TextField("0");
        Button btn = new Button("Count");
        btn.setOnAction(e -> {
            int value = Integer.parseInt(tf.getText());
            value++;
            tf.setText(String.valueOf(value));
        });
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.getChildren().addAll(label, tf, btn);

        Scene scene = new Scene(new StackPane(root), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}