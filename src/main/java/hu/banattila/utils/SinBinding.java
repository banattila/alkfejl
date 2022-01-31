package hu.banattila.utils;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableNumberValue;

public class SinBinding extends DoubleBinding {

    private ObservableNumberValue value;

    public SinBinding(ObservableNumberValue value){
        this.value = value;
        this.bind(value);
    }

    @Override
    protected double computeValue() {
        return Math.sin(this.value.doubleValue());
    }
}
