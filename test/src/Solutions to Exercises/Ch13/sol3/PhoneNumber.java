import java.io.Serializable;

class PhoneNumber implements Serializable
{
  // Read a phone number from the keyboard
  public static PhoneNumber readNumber()
  {
    FormattedInput in = new FormattedInput();

    int maxTries = 5;                                  // Maximum number of errors in input
    String area = null;                                // Stores the area code
    String localcode = null;                           // Stores the local code

    for(;;)                                            // Loop to allow retries
    {
      try {
        // Read in the area code
        if(area == null)                               // If there's no area code
        {
          System.out.println("\nEnter the area code:");
          area = Integer.toString(in.readInt());       // read one from the k/b
        }

        // Read in the number
        if(localcode == null)                          // If there's no local code
        {
          System.out.println("Enter the local code:");
          localcode = Integer.toString(in.readInt());  // read one from the k/b
        }

        System.out.println("Enter the number:");      
        String code = Integer.toString(in.readInt());  // Read last part of the number 
        localcode += " " + code;                       // and append to local code
        return new PhoneNumber(area,localcode);
      } catch(InvalidUserInputException e) {
        if(--maxTries == 0)                           //  If there were maxTries errors 
        {
          System.out.println("Maximum number of errors exceeded. Terminating...");
          System.exit(1);                             // then quit
        }
        System.err.println(e.getMessage()+"\nTry again");
        continue;                                     // otherwise try again
      }
    }
  }

  public PhoneNumber(String areacode, String number)
  {
    this.areacode = areacode;
    this.number = number;
 
 }

  public String toString()
  {  return areacode + ' ' + number;  }

  public int hashCode() {
    return 7*areacode.hashCode()+13*number.hashCode();
  }

  public boolean equals(Object phoneNumber) {
    PhoneNumber pn = (PhoneNumber)phoneNumber;
    return areacode.equals(pn.areacode) && number.equals(pn.number);
  }

  private String areacode;
  private String number;
}
