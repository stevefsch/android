// Chapter 8 Exercise 3

// Tests that the ParseInput works for '/' separator

public class TestParseInput {
  public static void main(String[] args) {
    char separator = '/';
    ParseInput tokenizer = new ParseInput(separator);
  
  
  System.out.println("Enter items separated by "+separator+":"); 
  String[] input = tokenizer.readTokens();

  System.out.println("The items that you entered are:"); 
  if(input != null)
    for( int i = 0 ; i< input.length ; i++)
      System.out.print("  "+input[i]);
  }
}