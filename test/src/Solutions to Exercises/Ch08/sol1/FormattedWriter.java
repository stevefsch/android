// Chapter 8 Exercise 1

import java.io.PrintWriter;
import java.io.Writer;
public class FormattedWriter extends PrintWriter {
  public final static int LEFT_JUSTIFIED  = 1;
  public final static int RIGHT_JUSTIFIED = 2;
  public final static int CENTERED = 3;                                 // Constant for centered output ***
  private int justification = RIGHT_JUSTIFIED; 

  private int width = 0;                // Field width required for output

  // Constructor with a specified field width, autoflush, and justification 
  public FormattedWriter(Writer output, boolean autoflush, int width, int justification) {
    super(output, autoflush);     // Call PrintWriter constructor
    if(width>0)
      this.width = width;           // Store the field width
    if(justification == LEFT_JUSTIFIED || justification == RIGHT_JUSTIFIED || justification == CENTERED)
      this.justification = justification; 
  }

  // Constructor with a specified field width
  public FormattedWriter(Writer output, int width) {
    this(output, false, width, RIGHT_JUSTIFIED);        
  }

  // Constructor with a specified field width and justification
  public FormattedWriter(Writer output, int width, int justification) {
    this(output, false, width, justification);        
  }

  // Constructor with a specified field width and autoflush option 
  public FormattedWriter(Writer output, boolean autoflush, int width) {
    this(output, autoflush, width, RIGHT_JUSTIFIED); 
  }

  // Output type double formatted in a given width
  public void print(double value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type double formatted in a given width on a line
  public void println(double value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type String formatted in a given width
  public void print(String str) {
    super.print(pad(str));   // Pad to width and output
  }

  // Output type String formatted in a given width on a line
  public void println(String str) {
    super.println(pad(str));   // Pad to width and output
  }

  // Output type float formatted in a given width
  public void print(float value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type float formatted in a given width on a line
  public void println(float value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type int formatted in a given width
  public void print(int value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type int formatted in a given width on a line
  public void println(int value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type long formatted in a given width
  public void print(long value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type long formatted in a given width on a line
  public void println(long value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type boolean formatted in a given width
  public void print(boolean value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type boolean formatted in a given width on a line
  public void println(boolean value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type char formatted in a given width
  public void print(char value) {
    super.print(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Output type char formatted in a given width on a line
  public void println(char value) {
    super.println(pad(String.valueOf(value)));   // Pad to width and output
  }

  // Helper method to form string
  private String pad(String str) {
    if (width == 0) {
      return str;

    }
      int blanks = width - str.length();         // Number of blanks needed
      StringBuffer result = new StringBuffer();  // Will hold the output
 
     if(blanks<0) {                              // Data does not fit
        for(int i = 0 ; i<width ; i++)
          result.append('X');                    // so append X's
        return result.toString();                // and return the result       
      }

      // New code to deal with justification replaces ***    
      // that is commented out below                  ***
      if(blanks==0)                               // If we need some blanks
        return result.append(str).toString();

      switch(justification) {
        case LEFT_JUSTIFIED:
          result.append(str);                      // Append value string
          for(int i = 0 ; i<blanks ; i++)
            result.append(' ');                    // then append blanks
          break;
        case RIGHT_JUSTIFIED:
          for(int i = 0 ; i<blanks ; i++)
            result.append(' ');                    // Append blanks
          result.append(str);                      // then append value string
          break;
        case CENTERED:
          for(int i = 0 ; i<blanks/2 ; i++)        // append half the blanks
            result.append(' ');                    
          result.append(str);                      // then append the value string
          for(int i = 0 ; i<blanks-blanks/2 ; i++) // then append remaining blanks
            result.append(' ');                    
          break;
      }
          
//      *** Old code ***
//      if(blanks>0)                               // If we need some blanks
//        for(int i = 0 ; i<blanks ; i++)
//          result.append(' ');                    // append them

      // Insert the value string at the beginning or the end
//      result.insert(justification == LEFT_JUSTIFIED ? 0 : result.length(),
//                                                                     str);
      return result.toString();
  }

  // Set the field width for output
  public void setWidth(int width) {
    if(width >= 0)
     this.width = width;
  }
}
