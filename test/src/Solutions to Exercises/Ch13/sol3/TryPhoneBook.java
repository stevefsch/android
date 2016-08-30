// Chapter 13 Exercise 3
/*
  To make the phone book searchable using a number as well as a name
  we need to do several things:
   
  1. Add a hashCode() method to the PhoneNumber class
  2. Add an equals() method to the PhoneNumber class to allow key comparisons.
  3. When we add a new entry, we must add two entries for the person,
     one keyed on the name, and the other keyed on the number.
  4. Add an overloaded getEntry() method to the PhoneBook class to retrieve an 
     entry using a PhoneNumber as a key.
  5. Add a getName() method to the Person class.
  6. Modify the listEntries() method in the PhoneBook class to accommodate
     keys of two different types, and the duplication of entries.
  7. Add the code to allow searching based on a number.
  
*/

class TryPhoneBook {
  public static void main(String[] args) {
    PhoneBook book = new PhoneBook();                 // The phone book
    FormattedInput in = new FormattedInput();         // Keyboard input
    Person someone;
    BookEntry entry = null;
    int what = 0;
    for(;;) {
      System.out.println("Enter 1 to enter a new phone book entry\n"+
                         "Enter 2 to find the number for a name\n"+
                         "Enter 3 to list all the entries\n" +
                         "Enter 4 to find the name for a number\n" +
                         "Enter 9 to quit.");
      try {
        what = in.readInt();
      } catch(InvalidUserInputException e) {
        System.out.println(e.getMessage()+"\nTry again.");
        continue;
      }
      switch(what) {
        case 1:
          book.addEntry(BookEntry.readEntry());
          break;
        case 2:
          someone = Person.readPerson();
          entry = book.getEntry(someone);
          if(entry == null)
            System.out.println("The number for "+someone +
                             " was not found. ");
          else           
            System.out.println("The number for "+someone +
                             " is " + book.getEntry(someone).getNumber());
          break;
        case 3:
          book.listEntries();
          break;
        case 4:
          PhoneNumber number = PhoneNumber.readNumber();
          entry = book.getEntry(number);
          if(entry == null)
            System.out.println("No entry for the number "+number +
                             " was found.");
          else           
            System.out.println("The name corresponding to the number "+number +
                             " is " + book.getEntry(number).getPerson().getName());
          break;
        case 9:
          book.save();
          System.out.println("Ending program.");
          return;
        default:
          System.out.println("Invalid selection, try again.");
          break;
      }
    }
  }
}
