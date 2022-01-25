import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
*A simple example for Java beans
*/
public class Person {

    private String name;
    private int age;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setName(String newName){
        String oldName = this.name;
        this.name = newName;

        pcs.firePropertyChange("name", oldName, newName);
    }

    public void setAge(int age){
        int oldAge = this.age;
        this.age = age;

        pcs.firePropertyChange("age", oldAge, age);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }
}
