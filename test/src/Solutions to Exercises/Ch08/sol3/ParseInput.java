// Chapter 8 Exercise 3

import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Vector;

public class ParseInput {
  private StreamTokenizer tokenizer;
  private char separator = ',';

  // Constructor requires the separator character as the argument
  public ParseInput(char separator){
    this.separator = separator;
    tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    setTokenizerState();
  }

  // Sets the tokenizer to accept anyhing except the separator and end-of-line as
  // part of a word
  private void setTokenizerState() {
    tokenizer.resetSyntax();              
    tokenizer.wordChars('\u0000',(char)(separator-1));    // Everything is a word character
    tokenizer.wordChars((char)(separator+1),'\u00ff');    // except for the separator
    tokenizer.whitespaceChars('\n','\n');                 // Make end-of-line whitespace(and therefore a word delimiter)
    tokenizer.whitespaceChars(separator,separator);       // Delimiter passed to constructor seaparates words
    tokenizer.eolIsSignificant(true);                     // End-of-line to be reported as TT_EOL
    
  }

  // Read tokens from the input
  public String[] readTokens() {
    int type = 0;                                         // Store the value returned by nextToken()
    Vector tokens = new Vector();                         // Will store the tokens that we find
    try {
      while((type = tokenizer.nextToken()) != StreamTokenizer.TT_EOL) {   // As long as we don'y have EOL
        if(type == StreamTokenizer.TT_WORD)                               // Check for a word
          tokens.add(tokenizer.sval);                                     // and save it in the vector
        else
          assert false;                                                   // We only expect words
      }
    }
    catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // Now transfer the tokens stored in the vector to a String[] array
    String[] strings = new String[tokens.size()];
    for(int i = 0 ; i<strings.length; i++ )
      strings[i] = (String)tokens.elementAt(i);
 
    return strings;    
  }

  // Convenience method to change the separator
  // We don't use this in the test program
  public void setSeparator(char separator) {
    this.separator = separator;
    setTokenizerState();
  }
}