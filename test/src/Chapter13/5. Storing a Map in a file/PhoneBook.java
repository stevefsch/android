import java.util.*;
import java.io.*;

class PhoneBook implements Serializable {
  public void addEntry(BookEntry entry) {
    phonebook.put(entry.getPerson(), entry);
  }

  public BookEntry getEntry(Person key) {
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
    LinkedList persons = new LinkedList(phonebook.keySet());
    Collections.sort(persons);                      // Sort the keys
    Iterator iter = persons.iterator();             // Get iterator for sorted keys

    while(iter.hasNext())
      System.out.println(phonebook.get((Person)iter.next()));
  }

  private File filename = new File("Phonebook.bin"); 
  private HashMap phonebook = new HashMap();
}

