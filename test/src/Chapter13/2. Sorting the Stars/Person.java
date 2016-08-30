public class Person implements Comparable {
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

  private String firstName;    // First name of person
  private String surname;      // Second name of person
}

