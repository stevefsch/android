// Chapter 11 Exercise 5
// This program writes names and addresses to a binary file.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;

public class NameAndAddressFile {
  // Directory and file to store names & addresses
  private static String directory = "C:/Beg Java Stuff";
  private static String filename = "NamesAndAddresses.bin";

  // Buffered keyboard input stream
  private static BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

  // I/O streams and channels for file
  private static FileInputStream inStream = null;
  private static FileOutputStream outStream = null;
  private static FileChannel inChannel = null;
  private static FileChannel outChannel = null;

  
  public static void main(String[] args) {
    File dir = new File(directory);
    if(!dir.isDirectory())
      dir.mkdir();
    File file = new File(dir, filename);

    // Create input and output streams for file
    // and corresponding input and output channels
    try {
      outStream = new FileOutputStream(file, true);
      inStream = new FileInputStream(file);
      inChannel = inStream.getChannel();
      outChannel = outStream.getChannel();
    } catch(FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      System.exit(1);
    }
    
    // Get operation selection from the keyboard
    while(true) {  
      switch(selectOperation()) {
        case 'N': case 'n':
          // Read a new name and address
          addNewNameAndAddress();
          break;
        case 'D': case 'd':
          // Display the contents of the file
          displayFileContents();
          break;
        case 'Q': case 'q':
          // Quit the program
          System.exit(0);
          break;
        default:
          System.out.println("Invalid Input. Try again.");
          break;
      }
    }
  }

  // Method to read a character entered on the keyboard
  private static char selectOperation() {
    System.out.println("Enter a letter to select an operation:"
                       +"\n Q or q to quit the program."
                       +"\n N or n to enter a new name and address."
                       +"\n D or d to display the contents of the file.\n");
                            
    String s = null;
    try {
    while((s= kb.readLine()) == null);
    } catch (IOException e) {
        e.printStackTrace(System.err);
        System.exit(1);
    }
    
    return s.trim().charAt(0);
  }

  // Method to output the contents of the file to the command line
  private static void displayFileContents() {
    ByteBuffer lengthBuf = ByteBuffer.allocate(4);  // Buffer for record length in characters
    ByteBuffer dataBuf = null;                      // Reference to a buffer to hold a record

    try {    
      inChannel.position(0L);                       // Go to the beginning
      while(inChannel.read(lengthBuf) != -1) {      // Loop until EOF                                 // Loop until EOF found
        lengthBuf.flip();                           // Ready to get length
        dataBuf = ByteBuffer.allocate(2*lengthBuf.getInt());  // Create record buffer of appropriate size
        lengthBuf.clear();                          // Clear length buffer for next iteration
        inChannel.read(dataBuf);                    // Read the next record
        dataBuf.flip();                             // Flip to access buffer contents

        // Since the lines in the name and address are separated by '\n'
        // we can use a tokenizer to extract each line in turn
        // We just need to get the buffer contents in the form of a string
        // and pass that to the tokenizer constructor
        StringTokenizer st = new StringTokenizer(dataBuf.asCharBuffer().toString(), "\n");

        System.out.println();                      // Blank line
        while(st.hasMoreTokens())                  // While there are more lines
          System.out.println(st.nextToken());      // Output the next line
      }
    }catch(IOException e) {
      e.printStackTrace();
      System.exit(1); 
    }
    return;    
  }

  private static void addNewNameAndAddress() {
    StringBuffer sb = new StringBuffer();    // Will hold the name & Address
    try {
      // The name will be stored in sb as a single line:
      //               "firstname secondname\n"
      System.out.println("Enter first name:");
      sb.append(kb.readLine()).append(' ');    
      System.out.println("Enter second name:");
      sb.append(kb.readLine()).append('\n'); 

      // We will add each address line to sb terminated by '\n'
      System.out.println("Enter address one line at a time. Enter ! to end:");
      String s = null; 
      while((s = kb.readLine()).trim().charAt(0) != '!')
       sb.append(s.trim()).append('\n');
    }
    catch(IOException e) {
      System.err.println("Error reading from keyboard\n");
      e.printStackTrace();
      System.exit(1); 
    }   

    // Now we can write the name and address to the file
    // We will write the length(character count) of the name and address first,
    // then the name and address itself
    ByteBuffer buf = ByteBuffer.allocate(2*sb.length()+4);
    buf.putInt(sb.length());
    char[] nameAndAddress = sb.toString().toCharArray();
    for(int i = 0 ; i<nameAndAddress.length ; i++)
      buf.putChar(nameAndAddress[i]);
    buf.flip();
    try {
      outChannel.write(buf);
    } catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
