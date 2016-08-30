/* 
  This version of the phonebook will use both String objects containing the second name 
  and PhoneNumber objects as keys. The values in both cases will be linked list objects
  containing the BookEntry objects that correspond to that second name or number.
*/
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class PhoneBook implements Serializable {

  /*
    We will assume that each person only has one number but several people can share
    the same number.
    
    Adding an entry will now require several steps. There are two entries to be added
    to the phone book, one for a number and one for the second name. Entries will always
    be a linked list of BookEntry objects keyed either on the number or the second name.
    
    Before adding each entry it will be necessary to check whether the key already exists,
    but names and numbers need to be treated slightly differently because for a given person
    there is only one number but several persons may share the same number. 
    
    Note that even if a name key already exists, the number key may not exist
    because there could be an existing entry for a name, but with a different number.

    The reverse is also true. Two more people may share the same number. In this case
    when a person after the first is entered, the number exists but the name does not. 
  */
  public void addEntry(BookEntry entry) {
    String name = entry.getPerson().getSecondname();
    PhoneNumber number = entry.getNumber();

    if(phonebook.containsKey(name))          // The name exists
      updateEntry(name, entry);              // so update existing entry for the name
    else
      addNewEntry(name, entry);
      
    if(phonebook.containsKey(number))        // If the number exists
      updateEntry(number,entry);             // update the entry for the number
    else 
      addNewEntry(number,entry);             // otherwise create a new list entry
  }
  
  private void updateEntry(Object key, BookEntry entry) {
    LinkedList list = (LinkedList)(phonebook.get(key));
    assert(list != null);

    // For updating a list keyed on a name, we need some special action
    if(!(key instanceof PhoneNumber)) {
      // We are dealing with an entry corresponding to a name key.
      // If there is an entry in the list corresponding to the same Person object
      // as the new entry this implies we are updating the number for an existing
      // person so we should remove the existing entry before adding the new one.
      BookEntry[] entries = new BookEntry[list.size()];
      list.toArray(entries);                      // Get the list entries as an array

      // Check for an entry corresponding to the same Person as the new entry.
      for(int i = 0 ; i<entries.length ; i++)
        if(entries[i].getPerson().equals(entry.getPerson())){  // We found one
          list.remove(entries[i]);                             // so delete it
          
          // Now we must also remove the entry corresponding to the old number
          LinkedList numberList = (LinkedList)phonebook.get(entries[i].getNumber());
          if(!numberList.remove(entries[i]))
            System.out.println("No number entry for "+entries[i]);
          break; 
        }                                                      // and quit the loop
    }

    // Now we can add the new entry - whatever it is
    list.add(entry);
    phonebook.put(key, list);
  }
  
  private void addNewEntry(Object key, BookEntry entry) {
    LinkedList list = new LinkedList();
    list.add(entry);
    phonebook.put(key, list);
  }

  // Gets entries corresponding to a second name
  // or a PhoneNumber object
  public BookEntry[] getEntries(Object key) {
    LinkedList list = (LinkedList)(phonebook.get(key));
    if(list == null) { 
      return new BookEntry[0];
    }else {
      BookEntry[] entries = new BookEntry[list.size()]; 
      return (BookEntry[])(list.toArray(entries));
    }
  }

  // Returns the entry corresponding to a Person object
  // or null if there is none
  public BookEntry getEntry(Person person) {
    BookEntry[] entries =  getEntries(person.getSecondname());
    BookEntry entry = null;
    for(int i = 0 ; i<entries.length ; i++){
      if(entries[i].getPerson().equals(person)) {
        entry = entries[i];
        break;
      }
    }
    return entry;
  }

  public PhoneBook() {
    if(filename.exists())
    try {
      ObjectInputStream in = new ObjectInputStream(
                                 new FileInputStream(filename));
      phonebook = (HashMap)in.readObject();
      in.close();
    } catch(ClassNotFoundException e) {
      System.out.println(e);
    } catch(IOException e) {
      System.out.println(e);
    }
  }

  public void save() {
    try {
      System.out.println("Saving phone book");
      ObjectOutputStream out = new ObjectOutputStream(
                                 new FileOutputStream(filename));
      out.writeObject(phonebook);
      System.out.println(" Done");
      out.close();
    } catch(IOException e) {
      System.out.println(e);
    }
  }

  // List all entries in the book
  public void listEntries() {
    // Get the keys as a list
    LinkedList keys = new LinkedList(phonebook.keySet());
    
    // Extract just the second name keys ignoring the PhoneNumber keys
    LinkedList names = new LinkedList();
    Iterator iter = keys.iterator();             // Get iterator for the keys
    while(iter.hasNext()) {
      Object key = iter.next();
      if(key instanceof String)
      names.add(key);
    }
    
    // Now we can list the entries for the Person keys
    Collections.sort(names);                        // Sort the second name keys
    iter = names.iterator();                      // Get iterator for sorted keys
    LinkedList list = null;
    Iterator entryIter = null;
    while(iter.hasNext()){
      list = (LinkedList)(phonebook.get((String)(iter.next())));
      entryIter = list.iterator();
      while(entryIter.hasNext()) {
        System.out.println((BookEntry)(entryIter.next()));
      }
    }
  }

  private File filename = new File("Phonebook2.bin"); 
  private HashMap phonebook = new HashMap();
}

