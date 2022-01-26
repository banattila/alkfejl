import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableNumberValue;

/**
 * Low level binding
 */

public class SquareRootBinding extends DoubleBinding {

    private ObservableNumberValue value;

    public SquareRootBinding(ObservableNumberValue o){
        this.value = o;
        //Important, because without this line, not will be working
        this.bind(o);
    }

    @Override
    public double computeValue(){
        return Math.sqrt(this.value.doubleValue());
    }

    public String toString(){
        return "" + this.get();
    }
}
