import java.io.*;

public class Person implements Comparable, Serializable {
  // Constructor
  public Person(String firstName, String surname) {
    this.firstName = firstName;
    this.surname = surname;
  }

  public String toString() {
    return firstName + " " + surname;
  }


  // Compare Person objects
  public int compareTo(Object person) {
    int result = surname.compareTo(((Person)person).surname);
    return result == 0 ? firstName.compareTo(((Person)person).firstName):result;
  }


  public boolean equals(Object person) {
    return compareTo(person) == 0;
  }

/*
  public int hashCode()
  {
    return 7*firstName.hashCode()+13*surname.hashCode();
  }
*/

  // Read a person from the keyboard
  public static Person readPerson() {
    FormattedInput in = new FormattedInput();
    // Read in the first name and remove blanks front and back
    System.out.println("\nEnter first name:");
    String firstName = in.stringRead().trim();

    // Read in the surname, also trimming blanks
    System.out.println("Enter surname:");
    String surname = in.stringRead().trim();
    return new Person(firstName,surname);
  }


  private String firstName;    // First name of person
  private String surname;      // Second name of person
}
