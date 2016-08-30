// Chapter 13 Exercise 4
/*
   To allow searching using just a second name we need a new strategy for
   how we store entries in the map. We can't just add second names as keys
   since it is more than likely there will be duplicate second names
   from time to time and we cannot have duplicate keys.
   
   One approach is to use the second name as a key to store all names and  
   a liked list as the value. The linked list will hold all the entries  
   corresponding to that second name. This has a useful side effect in that it 
   will accommodate multiple entries with the same name but different numbers -
   something that the previous implementation did not allow for.
   
   To continue to allow searching for a number, we will also have PhoneNumber
   objects as keys.
   
   We will use a different name for the file here since the file structure will
   be different from the previous exercise.
*/

class TryPhoneBook {
  public static void main(String[] args) {
    PhoneBook book = new PhoneBook();                 // The phone book
    FormattedInput in = new FormattedInput();         // Keyboard input
    Person someone = null;
    BookEntry[] entries = null;
    BookEntry entry = null;
    int what = 0;
    for(;;) {

      // I chose to group related input numbers together rather than force
      // consistency with the solution to Chapter 13 Ex 2. 
      System.out.println("Enter 1 to enter a new phone book entry\n"+
                         "Enter 2 to find the numbers for a second name\n"+
                         "Enter 3 to find the number for a first and second name\n"+
                         "Enter 4 to find the name for a number\n" +
                         "Enter 5 to list all the entries\n" +
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
          // Get the second name
              System.out.println("\nEnter second name:");
              String name = in.readString().trim();
              entries = book.getEntries(name);
              if(entries.length == 0) {
                System.out.println("No entries found for "+name);
              } else {
              System.out.println("Entries found for "+name+":");
                for(int i = 0 ; i<entries.length ; i++)
                  System.out.println("\n"+ entries[i]);
              }

          break;
        case 3:
          someone = Person.readPerson();
          entry = book.getEntry(someone);
          if(entry == null)
            System.out.println("The number for "+someone +
                             " was not found. ");
          else           
            System.out.println("The number for "+someone +
                             " is " + book.getEntry(someone).getNumber());
          break;
        case 4:
          PhoneNumber number = PhoneNumber.readNumber();
          entries = book.getEntries(number);
          if(entries.length == 0){
            System.out.println("No entry for the number "+number +
                             " was found.");
          } else {           
              System.out.println("Entries found for "+number+":");
                for(int i = 0 ; i<entries.length ; i++)
                  System.out.println("\n"+ entries[i]);
          }
          break;
        case 5:
          book.listEntries();
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
