// Chapter 11 Exercise 1

// Reads the file containing proverbs separated by '*'
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.BufferUnderflowException;
import java.nio.channels.FileChannel;

public class ReadProverbs {

  public static void main(String[] args) {
    String dirName = "c:/Beg Java Stuff";   // Directory for the output file
    String fileName = "Proverbs.txt";       // Name of the output file

    File aFile = new File(dirName, fileName);

    FileInputStream inputFile = null;
    try {
      inputFile = new FileInputStream(aFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.err);
      System.exit(1);
    } 
    FileChannel inChannel = inputFile.getChannel();

    ByteBuffer buf = ByteBuffer.allocate(100);              
    StringBuffer proverb = new StringBuffer();   // Will hold a proverb                                

    char separator = '*';                        // Separator between proverbs in the file
    char ch = 0;                                 // Stores a character from the buffer                                             
    while(true) {
      try{
        if(inChannel.read(buf) < 0)              // If we read EOF
          break;                                 // break out of the loop
      } catch(IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
      
      buf.flip();                               // Flip buffer ready for extracting proverb(s)
      try {
        while(true) { 
          if((ch=buf.getChar()) != separator)   // If the character from the buffer is not a separator
            proverb.append(ch);                 // append it to the proverb string
          else {                                // Otherwise we have a complete proverb
            System.out.println(proverb.toString());  // so display it
            proverb.delete(0,proverb.length());      // and remove it from the string buffer
          }
        }
      }catch(BufferUnderflowException bue) {    // We get to here when we reach the end of the buffer
          buf.compact();                        // so compact the buffer ready to read some more
        }
    }

    // The last proverb has no separator at the end so we must provide for
    // displaying it when file reading is finished.
    if(proverb.length()>0)
      System.out.println(proverb.toString());
  }             
}