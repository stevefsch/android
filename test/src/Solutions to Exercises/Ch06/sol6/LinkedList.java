// Chapter 6 Exercise 6

// Modified to support insertion and deletion of elements
// Additions and modifications are marked by ***
public class LinkedList {
  private ListItem start;              // First ListIem in the list
  private ListItem end;                // Last ListIem in the list
  private ListItem current;            // The current item for iterating

  // Constructor to create a list containing one object
  public LinkedList(Object item) {
    start = new ListItem(item);           // item is the start
    current = end = start;                // as well as the end and current
  }

  // Construct a linked list from an array of objects
  public LinkedList(Object[] items) {
    // Create a one item list
    start = new ListItem(items[0]);       // 1st item is the start
    end = start;                          // as well as the end

    // Now add the other items
    for(int i = 1; i < items.length; i++)
      addItem(items[i]);
  }

  // Add an item object to the list
  public void addItem(Object item) {
    ListItem newEnd = new ListItem(item); // Create a new ListItem
    end.setNext(newEnd);                  // Set next variable for old end as new end
    newEnd.setPrevious(end);              // So previous for new item   
    current = end = newEnd;               // Store new item as end and current 
  }

  // Get the first object in the list
  public Object getFirst() {
    current = start;
    return start.getObject();
  }

  // Additional method to get the last object in the list    
  public Object getLast() {
    current = end;
    return end.getObject();
  }

  // Get the next object in the list                                ***
  // Because we are going to operate on the current item
  // with insert() and delete() we should ensure current always
  // references an item in the list, unless the list is empty
  public Object getNext() {
    if(current == null)                  // List is empty
      return null;
 
    // List is not empty
    ListItem item = current.getNext();   // Get the next item
    if(item == null)                     // If it is null we are at the end
      return null;
    else
    {
      current = item;                    // Set current to the next item
      return current.getObject();
    }
  }

  // Method to get the previous object in the list                   ***
  // Because we are going to operate on the current item
  // with insert() and delete() we should ensure current always
  // references an item in the list, unless the list is empty
  public Object getPrevious() {
    if(current == null)                  // List is empty
      return null;

    // List is not empty
    ListItem item = current.getPrevious(); // Get previous item
    if(item == null)                       // If it is null we are at the start
      return null;
    else {
      current = item;                      // Set current to the next item
      return current.getObject();
    }
  }

  // Insert an item following the current item                       ***
  public void insert(Object item) {
    ListItem newItem = new ListItem(item);         // Create the new list item
    if(current == null) {                          // Check for empty list
      current = start = end = newItem;             // Add new item
      return;
    }

    // Here the list is not empty
    ListItem oldNext = current.getNext();          // Get the old item following current
    current.setNext(newItem);                      // Set next link for current to new item
    newItem.setPrevious(current);                  // Set previous link for new item    
    newItem.setNext(oldNext);                      // Set next link for new item

    if(oldNext != null)                            // If we have an old next
      oldNext.setPrevious(newItem);                // Set its previous link to new item
    current = newItem;                             // Make new item current
  }

  // Delete the current item                                         ***
  void delete() {
    if(current == null)                            // Check if list is empty
      return;

    // List is not empty
    ListItem nextItem = current.getNext();         // Get the item following current  
    ListItem previousItem = current.getPrevious(); // Get the item before currrent

    if(previousItem != null)                       // If the previous item is not null
      previousItem.setNext(nextItem);              // Set the link to the next
    else                                           // current must have been first
      start = nextItem;                            // so next item is new first

    if(nextItem != null)                           // If the next item is not null
      nextItem.setPrevious(previousItem);          // Set the link to the previous
    else                                           // current must have been last
      end = previousItem;                          // previous item is new end

    // Set the current as previous, as long as it is not null
    current = (previousItem != null) ? previousItem : nextItem;
  }

  // Method to insert the object specified by the first argument
  // after the object specified by the second argument.
  // If the first argument is not found the second argument is not inserted
  // The method returns true for success, and false for failure
  public boolean insert(Object inserted, Object position) {
    if(start==null)            // If list is empty, start will be null
      return false;

    // Search for position
    ListItem theItem = start;
    while(theItem != null)
      if(theItem.getObject().equals(position)) {    
        current = theItem;    
        insert(inserted);
        return true;
      }
      else
        theItem = theItem.getNext();

    // If we reach here we didn't find position
    return false;    
  }
}





