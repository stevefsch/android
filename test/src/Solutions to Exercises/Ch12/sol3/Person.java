// Class encapsulating a person's name and address
import java.io.Serializable;
public class Person implements Serializable {
  private Name name;
  private Address address;
  
  public Person(Name name, Address address) {
    this.name = name;
    this.address = address;
  }
  
  public String toString() {
    return name.toString()+"\n"+address.toString();
  }
  
}