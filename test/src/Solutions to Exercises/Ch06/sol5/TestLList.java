// Chapter 6 Exercise 5

public class TestLList {
  public static void main(String args[]) {
    LinkedList myList = new LinkedList();
    Double value = new Double(0.0);	// Only so getClass() is callable.
    String saying = "The cat sat on the mat.";
    char separator = ' ';
    int endIndex = 0;
    int beginIndex = 0;
    int noWords = 0;

// Append 5 Doubles to the list:
    for(int i=0;i<5;i++)
      myList.append(new Double(i));

// Append 5 Integers to the list:
    for(int i=0;i<5;i++)
      myList.append(new Integer(i));

// Output the total list length:
    System.out.println("Length of myList is " + myList.length());
    
// Output the contents of the list:
    System.out.println("List is "+myList);

// Add in some strings between the Doubles and Integers:
    // Step through the list to the desired position (end of Doubles):
    myList.first();
    do {
      myList.next();
    } while(myList.current().getItemClass() == value.getClass());

    // Find the number of words in the saying we want to insert into the list:
    do {
      endIndex++;
      noWords++;
    } while((endIndex = saying.indexOf(separator,endIndex)) != -1);

    // Add in each word as a separate element of the linked list:
    beginIndex = endIndex = 0;
    for(int i=0; i<noWords-1; i++) {
      endIndex = saying.indexOf(separator,beginIndex);
      myList.add(saying.substring(beginIndex,endIndex));
      myList.next();		// This is needed as add() keeps current at
      beginIndex = endIndex+1;  // same position wrt beginning of list.
    }
    myList.add(saying.substring(beginIndex));	// add the last word

    // Output the contents of the list:
    System.out.println("List is "+myList);

    // Delete all the strings from the list:
    myList.first();
    while(myList.current() != null) {
      // If it's a string:
      if(myList.current().getItemClass() == saying.getClass())
        myList.delete();         // delete it,
      else
        myList.next();           // otherwise skip over to the next item.
    }

    // Output the contents of the list:
    System.out.println("List is "+myList);
  }
}
