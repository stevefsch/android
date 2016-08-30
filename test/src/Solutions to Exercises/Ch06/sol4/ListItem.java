// Chapter 6 Exercise 4

// Modified to support backwards traversal of the list.
// Additions and modifications are marked by ***.

public class ListItem {
  ListItem next;             // Refers to next item in the list.
  ListItem previous;         // Refers to the previous item.       ***
  Object item;               // The item for this ListItem.

  // Constructor: 
  public ListItem(Object item) {
    this.item = item;        // Store the item.
    next = previous = null;  // Set next and previous to null.      ***
  }

  // Set the pointer to the next ListItem:
  public void setNext(ListItem next) {
    this.next = next;        // Store reference to the next item.
  }
  
  // Additional method to set the pointer to the previous ListItem:  ***
  public void setPrevious(ListItem previous) {                                                               
    this.previous = previous; // Store reference to the previous item. 
  }
 
  // Get the next item in the list:
  public ListItem getNext() {
    return next;
  }

  // Additional method to get the previous item in the list:         ***
  public ListItem getPrevious() {
    return previous;
  }

  // Get the object for this item:
  public Object getObject() {
    return item;
  }

  // Return class name & object:
  public String toString() {
    return "ListItem " + item;
  }
}

