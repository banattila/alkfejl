import java.beans.PropertyChangeEvent;

public class Main {
    /**
     *A simple example for Java beans
     */
    public static void handlePropertyChange(PropertyChangeEvent pce){
        String propName = pce.getPropertyName();
        if ("name".equals(propName)){
            System.out.println("Name has changed");
            System.out.println("Old name was: " + pce.getOldValue());
            System.out.println("New name is: " + pce.getNewValue());
        } else if("age".equals(propName)){
            System.out.println("Age has been changed");
            System.out.println("Old age was: " + pce.getOldValue());
            System.out.println("New age is: " + pce.getNewValue());
        }
    }


    public static void main(String[] args) {
        /**
         *A simple example for Java beans
         */
        Person p = new Person("Jani", 99);
        p.addPropertyChangeListener(Main::handlePropertyChange);
        p.setName("Sanyi");
        p.setAge(21);

        /**
         * A simple example for Javafx beans
         */

        Monitor m = new Monitor();
        System.out.println(m);
        m.setScreenType("Non-flat");
        System.out.println(m);
        m.setSize(56);
        System.out.println(m);
    }
}
