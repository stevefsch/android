// Chapter 11 Exercise 4

// Dumps the content of a file as hexadecimal

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class HexFileDump {
  public static void main(String[] args) {
    if(args.length == 0 ) {
      System.out.println("No file specified. Usage is:"
                        +"\n\njava HexFileDump \"PATH_TO_FILE\""
                        +"\n\nwhere PATH_TO_FILE is an absolute path to the file whose you want to dump."
                        +"\nYou should use either forward slashes or double backslashes as separators in the path.");
      System.exit(1);
    }
    String filePath = args[0];
    File file = new File(filePath);
    if(!file.isFile()) {
      System.out.println("File "+filePath+" does not exist.");
      System.exit(1);
    }
    FileInputStream inFile = null;
   
    try  {
      inFile = new FileInputStream(file); 
    }
    catch(FileNotFoundException e) {
      e.printStackTrace(System.err);
      System.exit(1);
    }
    FileChannel inChannel = inFile.getChannel(); 
      
    // Create buffer to accommodate five groups of 8 hex digits separated by spaces.
    // Each byte is two hex digits so 4 bytes is 8 hex digits
    // so buffer capacity must be a multiple of 5*4 = 20 bytes.
    ByteBuffer buffer = ByteBuffer.allocate(160);     // Buffer for 8 lines of output

    int[] numbers = new int[40];    // We will retrieve each 4 bytes(8 hex digits) as an int value
    try {
        IntBuffer intBuffer = null;
        while(inChannel.read(buffer) != -1) {
         buffer.flip();                                     // Flip the buffer ready to get the data
         intBuffer = buffer.asIntBuffer();                  // Extract the bytes as int values

         // We can use the static toHexString() method in the Integer class to convert each int to hex.
         // However, this method omits leading zeroes so we will have to insert them if they are missing                        
          StringBuffer line = new StringBuffer(45);          // 5 groups of 8 hex digit characters plus 5 spaces (1 at the end)
          char[] zeroes = {'0','0','0','0','0','0','0','0'}; // Leading zeroes for insertion as necessary
          
          // Process all the data in the integer buffer
          for(int i = 0 ; i<intBuffer.limit() ; i++) {
            String hex = Integer.toHexString(intBuffer.get());   // Convert int to hex string
            if(hex.length()<8)                                   // If it's less than 6 characters
              line.append(zeroes, 0, 8-hex.length());            // Insert leading zeroes
            line.append(hex).append(' ');                        // Append the hex and a space
            if(line.length() == line.capacity()) {               // If the string buffer is full
              System.out.println(line.toString());               // display it
              line.delete(0,line.length());                      // and remove the contents
            } 
          }
          // Check for partially filled buffer and print it.
          if(line.length() > 0)
            System.out.println(line.toString());

          buffer.clear();                                   // Clear the buffer for the next read
        }
        System.out.println("\nEOF reached.");
        inFile.close();                                  // Close the stream and the channel
      }
      catch(IOException e) {
        e.printStackTrace(System.err);
        System.exit(1);
      }
  }
}