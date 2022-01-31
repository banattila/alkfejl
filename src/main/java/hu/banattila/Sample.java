package hu.banattila;

import hu.banattila.utils.SinBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Sample implements Initializable {

    @FXML
    private TextField tf;

    @FXML
    private Label computed;

    private void setComputed(){
        try {
            DoubleProperty property = new SimpleDoubleProperty();
            StringProperty stringProperty = tf.textProperty();
            stringProperty.addListener(((observableValue, s, t1) -> property.setValue(Double.parseDouble(t1))));
            SinBinding binding = new SinBinding(property);

            computed.textProperty().bind(binding.asString());
        } catch (NumberFormatException e){
            tf.setText("0");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComputed();
    }
}
