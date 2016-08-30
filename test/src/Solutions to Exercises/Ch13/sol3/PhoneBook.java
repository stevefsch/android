/*
 This version of the class stores each entry twice, once using the
 person as the key and once using the number as the key. This allows
 an entry to be accessed using either key.
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
  public void addEntry(BookEntry entry) {
    phonebook.put(entry.getPerson(), entry);
    phonebook.put(entry.getNumber(), entry);
  }

  public BookEntry getEntry(Person key) {
    return (BookEntry)phonebook.get(key);
  }

  public BookEntry getEntry(PhoneNumber key) {
    return (BookEntry)phonebook.get(key);
  }

  public PhoneNumber getNumber(Person key) {
    return getEntry(key).getNumber();
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
    
    // Extract just the Person keys ignoring the PhoneNumber keys
    LinkedList persons = new LinkedList();
    Iterator iter = keys.iterator();             // Get iterator for the keys
    while(iter.hasNext()) {
      Object key = iter.next();
      if(key instanceof Person)
      persons.add(key);
    }
    
    // Now we can list the entries for the Person keys
    Collections.sort(persons);                      // Sort the Person keys
    iter = persons.iterator();                      // Get iterator for sorted keys

    while(iter.hasNext())
      System.out.println(phonebook.get((Person)iter.next()));
  }

  private File filename = new File("Phonebook.bin"); 
  private HashMap phonebook = new HashMap();
}

