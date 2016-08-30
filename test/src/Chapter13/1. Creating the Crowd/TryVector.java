import java.util.*;
import java.io.*;

public class TryVector {
  public static void main(String[] args) {
    Person aPerson;                  // A person object
    Crowd filmCast = new Crowd();

    // Populate the crowd
    for( ; ; ) {                     // Indefinite loop
      aPerson = readPerson();        // Read in a film star
      if(aPerson == null)            // If null obtained...
        break;                       // We are done...
      filmCast.add(aPerson);         // Otherwise, add to the cast
    }

    int count = filmCast.size();
    System.out.println("You added " + count +
      (count == 1 ? " person":  " people") + " to the cast.\n");

    // Show who is in the cast using an iterator
    Iterator thisLot = filmCast.iterator();  // Obtain an iterator

    while(thisLot.hasNext())    // Output all elements
      System.out.println( thisLot.next() );
  }
  // Read a person from the keyboard
  static public Person readPerson() {
     FormattedInput in = new FormattedInput();

    // Read in the first name and remove blanks front and back
    System.out.println(
                     "\nEnter first name or ! to end:");
    String firstName = in.stringRead().trim();        // Read and trim a string

    if(firstName.charAt(0) == '!')                    // Check for ! entered
      return null;                                    // If so, we are done...
 
    // Read in the surname, also trimming blanks
    System.out.println("Enter surname:");
    String surname = in.stringRead().trim();          // Read and trim a string
    return new Person(firstName,surname);
  }
}
