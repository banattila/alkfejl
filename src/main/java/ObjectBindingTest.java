import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Testing the object binding on the Person, Monitor class
 */


public class ObjectBindingTest {

    public static void main(String[] args) {
        Person p1 = new Person("Jani", 55);
        Person p2 = new Person("Sanyi", 35);

        ObjectProperty<Person> person1 = new SimpleObjectProperty<>(p1);
        System.out.println(p1);
        ObjectProperty<Person> person2 = new SimpleObjectProperty<>(p2);
        System.out.println(p2);

        BooleanBinding isEquals = person1.isEqualTo(person2);
        System.out.println(isEquals.get());

        p2.setAge(55);
        p2.setName("Jani");
        System.out.println(isEquals.get());
        System.out.println(p1);
        System.out.println(p2);

        person2.set(p1);
        System.out.println(isEquals.get());


        Monitor m1 = new Monitor();
        Monitor m2 = new Monitor();
        ObjectBinding<Monitor> result = new When(m1.sizeProperty().greaterThan(m2.sizeProperty())).then(m1).otherwise(m2);
        m1.setSize(55);
        System.out.println(result.get());
    }
}
