import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;

class TryCalendar {
  public static void main(String[] args) {
    FormattedInput in = new FormattedInput();

    // Get the date of birth from the keyboard
    System.out.println("Enter your birth date as dd mm yyyy: ");
    int day = in.intRead();
    int month = in.intRead();
    int year = in.intRead();

    // Create birth date calendar – month is 0 to 11
    GregorianCalendar birthdate = new GregorianCalendar(year, month-1,day);
    GregorianCalendar today = new GregorianCalendar();  // Today's date

    // Create this year's birthday
    GregorianCalendar birthday = new GregorianCalendar(
                                        today.get(today.YEAR),
                                        birthdate.get(birthdate.MONTH),
                                        birthdate.get(birthdate.DATE));  

    int age = today.get(today.YEAR) - birthdate.get(birthdate.YEAR);

    String[] weekdays = new DateFormatSymbols().getWeekdays(); // Get day names

    System.out.println("You were born on a " + 
                        weekdays[birthdate.get(birthdate.DAY_OF_WEEK)]);
    System.out.println("This year you " + 
                        (birthday.after(today)   ?"will be " : "are ") +
                        age + " years old.");
    System.out.println("This year your birthday "+
                       (today.before(birthday)? "will be": "was")+
                       " on a "+ weekdays[birthday.get(birthday.DAY_OF_WEEK)]);
  }
}
