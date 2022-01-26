import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * A simple example for Javafx beans
 */

public class Monitor {

    private static final String DEFAULT_SCREEN_TYPE = "flat";
    private IntegerProperty size;
    private StringProperty screenType;

    public String getScreenType(){
        return(screenType != null) ? screenType.getValue() : DEFAULT_SCREEN_TYPE;
    }

    public int getSize(){
        return(size != null) ? size.getValue() : 24;
    }

    public void setScreenType(String newScreenType){
        if (screenType != null || !DEFAULT_SCREEN_TYPE.equals(newScreenType)){
            screenTypeProperty().setValue(newScreenType);
        }
    }

    public void setSize(int newSize){
        if (size != null && sizeProperty().get() != newSize){
            sizeProperty().set(newSize);
        }
    }

    public StringProperty screenTypeProperty(){
        if(screenType == null){
            screenType = new SimpleStringProperty(this, "screenType", DEFAULT_SCREEN_TYPE);
        }
        return screenType;
    }

    public IntegerProperty sizeProperty(){
        if(size == null){
            size = new SimpleIntegerProperty(this, "size", 24);
        }
        return size;
    }

    public String toString(){
        return "Monitor size is: " + sizeProperty().get() + " and display type is: " + screenTypeProperty().getValue();
    }
}
