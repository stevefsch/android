import java.util.Observer;
import java.util.Observable;

public class Person implements Observer
{
  String name;              // Person's identity
  String says;              // What they say when startled

  // Constructor
  public Person(String name, String says)
  {
    this.name = name;
    this.says = says;
  }

  // Called when observing an object that changes
  public void update(Observable thing, Object o)
  {
    System.out.println("It's " + ((JekyllAndHyde)thing).getName() +
                       "\n" + name + ": " + says);
  }
}
