package hu.alkfejl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    Scene scene1, scene2;

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);

        Button btn1 = new Button("Go to scene 2");
        btn1.setOnAction(e -> SceneManager.setActualScene("scene2"));
        HBox sp1 = new HBox();
        sp1.getChildren().add(btn1);
        ComboBox<String> cmb1 = new ComboBox<>();

        ObservableList<String> list1 = FXCollections.observableArrayList("egy", "kettő");
        cmb1.setItems(list1);
        cmb1.setValue("egy");
        sp1.getChildren().add(cmb1);
        scene1 = new Scene(sp1, 640, 480);

        Button btn2 = new Button("Got back to scene 1");
        btn2.setOnAction(e -> SceneManager.setActualScene("scene1"));
        StackPane sp2 = new StackPane();
        sp2.getChildren().add(btn2);
        scene2 = new Scene(sp2, 300, 300);


        SceneManager.addScene("scene1", scene1);
        SceneManager.addScene("scene2", scene2);
        SceneManager.setActualScene("scene1");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}