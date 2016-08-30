// Chapter 6 Exercise 5

class LinkedList {
    private int length = 0;
    protected ListItem first = null;		
    protected ListItem last = null;		
    protected ListItem current = null;		
						
    // Default constructor:
    public LinkedList() {
	    first = last = current = null;
      length = 0;
    }

    // Sets the current list item to the first list item:
    public void first() {
      	current = first;
    }

    // Sets the current list item to the last list item:
    public void last() {
      	current = last;
    }

    // Returns a reference to the current list item:
    public ListItem current() {
      	return current;
    }

    // Returns the number of items in the list:
    public int length() {
      	return length;
    }

    // Sets the current list item to the next list item:
    public void next() {
      	current = current.next;
    }

    // Sets the current list item to the previous list item:
    public void previous() {
      	current = current.previous;
    }

// Methods to manipulate the list:

    // Method to add an object to the end of the list:
    public void append(Object data) {
      	ListItem item = new ListItem(data);
      	if(length == 0)
           first = last = item;		// If list is empty set first and last correctly.
      	else
           last.next = item;		// Update next reference for the penultimate item to refer 
           item.previous = last;	// to the new item, and previous reference for the new item
           last = item;			// to refer to penultimate item. Set last reference to refer 
    	current = last;			// to the new item. Update the current position to last.
    	length++;			
    }

    // Method to add an object to the beginning of the list:
    public void prefix(Object data) {
    	ListItem item = new ListItem(data);
     	if(length == 0)		
      	   first = last = item;		// If list is empty set first and last correctly.
    	else
      	   first.previous = item;	// Update previous reference for the second item to refer 
      	   item.next = first;		// to the new item, and next reference for the new item
           first = item;		// to refer to the second item. Set first reference to refer 
     	current = first;		// to the new item. Update current position to first.
        length++;			
    }

    // Add an object at the current position, inserting before current:
    public void add(Object data) {
    	ListItem item = new ListItem(data);
     	ListItem previous;

    // If current refers to nothing, we must define where to put the data:
    	if(current == null) {
      	   if((first == null) && (last == null))	// If the list is empty, 
	      first = last = item;	            // add the new item, setting first and last.
           else				                    // If the list is not empty,
	      current = first;		              // add the new item at the beginning.
    	}
    	if(current == first) {		          // If so, current.previous is a null reference!
      	   first = item;		              // Update what first refers to current is now second in
           current.previous = item;	      // list, so make previous refer to the new first item.
           item.next = current;		        // Also make new first item refer to second.
    	}
        else {				                    // Otherwise current.previous refers to something.
      	   previous = current.previous;	  // Store reference current.previous in buffer previous.
           previous.next = item;	        // Make next reference from previous refer to new.
           current.previous = item;	      // Make previous reference from current refer to new.
      	   item.next = current;		        // Make next reference of new refer to current.
      	   item.previous = previous;	    // Make previous reference of new refer to previous.
    	}
    	current = item;			                // Set current to refer to the new item.
    	length++;			
    }

    // Delete an object at the current position:
    public void delete() {
    	ListItem previous = null, next = null;
    	if(current == null) {		// If no object is defined by current to delete:
      	   if(first == null)		
     		return;			// If the list is empty: can't delete non-existent objects.
      	   else				
		current = last;		// Otherwise: delete the last object if none specified.
    	}

    	// If current refers to the first or last items in the list
    	// the references first and last need updating appropriately:
    	if(current == first) {		               // If current refers to the first item make first
      	   first = current.next;	             // refer to the second item. Store next reference (second 
      	   next = current.next;		             // item) in a buffer. As the first item no longer 
           next.previous = null;	             // exists, make previous reference in second item null.	
    	}
    	else if(current == last) {					     // If current refers to the last item: make last
      	   last = current.previous;		         // refer to the penultimate item. Store previous reference
      	   previous = current.previous;		     // (penultimate item) in a buffer. As the last item no
           previous.next = null;		           // longer exists, make the next reference of previous null.
    	}
    	else {					                         // If current refers to neither first or last,
           previous = current.previous;		     // current.previous and current.next both refer to
           next = current.next;			           // list items. Store them. Join up the two references so
           previous.next = current.next;	     // that previous.next refers to next and next.previous
      	   next.previous = current.previous; 	 // refers to previous and current is no longer referred to.
    	}
    	if(previous == null)		                 // If current referred to the first item:
      	   current = next;		                 // make current refer to the second item.
    	else				                             // Otherwise make current refer to the
      	   current = previous;		             // item before the one deleted.
    	length--;			                           // Decrement the list length.
    }

    // Convert the entire list to a string:
    public String toString() {
    	StringBuffer ret = new StringBuffer(); 	 // Use StringBuffer as list is of variable length.
    	first();				                         // Set current to first.
    	while(current != null) {	           	   // While there are still elements in the list,
    					                                 // append their values to the StringBuffer.
       	   ret = ret.append(current.toString() + "\t");		// separated by tabs.
           next();
    	}
    	return ret.toString();	           	     // Return the StringBuffer as a String 
    }				           	                     
}
