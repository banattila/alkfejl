package hu.banattila;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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


        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        HBox lineOne = new HBox();
        lineOne.setSpacing(10);
        HBox lineTwo = new HBox();
        lineTwo.setSpacing(10);
        Label label = new Label("Enter a number");
        TextField tfInput = new TextField("0");
        Label labelTwo = new Label("Accumulated sum");
        TextField tfOutput = new TextField(tfInput.getText());
        tfOutput.setDisable(true);
        tfOutput.setEditable(false);
        tfInput.setOnKeyReleased(keyEvent-> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                int result = Integer.parseInt(tfOutput.getText());
                int input = 0;
                try {
                    input = Integer.parseInt(tfInput.getText());
                    result += input;
                    tfOutput.setText(String.valueOf(result));
                    tfInput.setText("");
                } catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.APPLY);
                    alert.showAndWait();
                }
            }
        });
        lineOne.getChildren().addAll(label, tfInput);
        lineTwo.getChildren().addAll(labelTwo, tfOutput);
        root.getChildren().addAll(lineOne, lineTwo);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}