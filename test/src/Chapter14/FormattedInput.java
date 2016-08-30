import java.io.*;

public class FormattedInput
{
  // Method to read an int value LISTED BELOW
  // Method to read an int value
  public int intRead()
  {
    try
    {
      for(int i = 0; i < 5; i++)
      {
        if(tokenizer.nextToken() == tokenizer.TT_NUMBER && 
                                           tokenizer.nval == (double)((long) tokenizer.nval))
          return (int)tokenizer.nval;    // Value is numeric, so return as int 
        else
        {
          System.out.println("Incorrect input: " + tokenizer.sval +
                             " Re-enter an integer");
          continue;                      // Retry the read operation
        }
      }  
      System.out.println("Five failures reading an int value" +
                                        " - program terminated");
      System.exit(1);                    // End the program
      return 0;
    }
    catch(IOException e)                 // Error reading in nextToken()
    {
      System.out.println(e);             // Output the error
      System.exit(1);                    // End the program
      return 0;
    }
  }

  // plus methods to read various other data types

  // Read a  string
  public String stringRead()
  {
    try
    {
      for(int i = 0; i < 5; i++)
      {
        int tokenType = tokenizer.nextToken();        // Read a token
        if(tokenType==tokenizer.TT_WORD || tokenType == '\"')   // Type is a string
          return tokenizer.sval;                                // so return it 
        else if(tokenType == '!')                     // Non-alpha returned as type
          return "!";                                 // so return end string
        else
        {
          System.out.println(
                    "Incorrect input. Re-enter a string between double quotes");
          continue;            // Retry the read operation
        }
      }  
      System.out.println("Five failures reading a string" +
                                        " - program terminated");
      System.exit(1);          // End the program
      return null;
    }
    catch(IOException e)       // Error reading in nextToken()
    {
      System.out.println(e);   // Output the error
      System.exit(1);          // End the program
      return null;
    }
  }

  

  // Object to tokenize input from the standard input stream
  private StreamTokenizer tokenizer = new StreamTokenizer(
                                      new InputStreamReader(System.in));
}
