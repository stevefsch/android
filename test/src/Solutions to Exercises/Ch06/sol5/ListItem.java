// Chapter 6 Exercise 5

class ListItem {
  protected ListItem previous;	
  protected ListItem next;	
  private Object data;		

// Constructors:
  // Default constructor  - sets all references to null:
  public ListItem() {
    previous = next = null;
    data = null;
  }

  // Constructor normally used - sets data to refer to the Object passed:
  public ListItem(Object data) {
    this.data = data;
    previous = next = null;
  }

  // Overrides the default toString in Object by the toString of data:
  public String toString() {
    return data.toString();	// Returns a string referring to the data,
  }				// not to the ListItem.

  // Returns the class of the data object (not of the Item itself):
  public Class getItemClass() {
    return data.getClass();
  }
}
