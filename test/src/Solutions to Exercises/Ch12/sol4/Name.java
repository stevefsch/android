// Class encapsulating a name
import java.io.Serializable;

public class Name implements Serializable {
  private String firstName;
  private String secondName;
  
  public Name(String firstname, String secondname) {
    firstName = firstname;
    secondName = secondname;
  }
  
  public String toString() {
    return firstName + " " + secondName;
  }
}