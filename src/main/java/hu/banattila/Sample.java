package hu.banattila;

import com.sun.javafx.scene.control.inputmap.InputMap;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Sample implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl2;

    private void setLbl(){
        ReadOnlyDoubleProperty width = root.widthProperty();
        ReadOnlyDoubleProperty height = root.heightProperty();
        StringProperty str = new SimpleStringProperty();
        width.addListener(((observableValue, number, t1) -> str.set("Width: " + number + ", Height: " + height.get())));
        height.addListener(((observableValue, number, t1) -> str.set("Width: " + width.get() + ", Height: " + number)));
        lbl.textProperty().bind(str);

        //Mouse position
        DoubleProperty posX = new SimpleDoubleProperty();
        DoubleProperty posY = new SimpleDoubleProperty();

        root.setOnMouseMoved(e -> {
            posX.set(e.getSceneX());
            posY.set(e.getSceneY());
        });
        StringProperty str2 = new SimpleStringProperty();
        posX.addListener(((observableValue, number, t1) -> str2.set("X position: " + number + ", Y position: " + posY.get())));
        posY.addListener(((observableValue, number, t1) -> str2.set("X position: " + posX.get() + ", Y position: " + number)));
        lbl2.textProperty().bind(str2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLbl();
    }
}
