import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Low level binding test
 */

public class LowLevelBindingTest {

    public static void main(String[] args) {

        DoubleProperty num = new SimpleDoubleProperty(100.0);
        DoubleProperty result = new SimpleDoubleProperty();
        DoubleBinding sqrt = new SquareRootBinding(num);
        result.bind(sqrt);
        System.out.println(sqrt);

        num.set(9);
        System.out.println(sqrt);
    }
}
