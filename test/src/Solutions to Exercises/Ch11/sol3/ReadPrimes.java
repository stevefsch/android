// Chapter 11 Exercise 3

// One integer command line argument is the position in the file of single prime required,
// the first being at position 1.
// If there are two integer command line arguments, the first is the count of the number of primes required
// and the second is the index position of the first prime required.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

public class ReadPrimes {
  public static void main(String[] args) {
    int requiredPrimeFrom = 0;         // Sequence number of first prime required - 1 for the first, 2 for the second etc.
    int totalPrimesRequired = 0;                // Count of total primes required
    if(args.length >= 2) {
      try {
        totalPrimesRequired = Integer.parseInt(args[0]);
        requiredPrimeFrom = Integer.parseInt(args[1]);
      } catch(NumberFormatException nfe) {
          System.out.println("Command line argument not a valid integer. Continuing to list file contents.");
          requiredPrimeFrom = 0;
        }
    }

    File aFile = new File("C:/Beg Java Stuff/primes.bin");
    FileInputStream inFile = null;
   
    try  {
      inFile = new FileInputStream(aFile); 
    } catch(FileNotFoundException e) {
        e.printStackTrace(System.err);
        System.exit(1);
      }
    FileChannel inChannel = inFile.getChannel(); 
    ByteBuffer buf = null;   

    // If a positive index position (starting from 1) for the position of the first prime 
    // that is required, go to that position in the file and read primes up to including
    // the last prime required.
    try {
        int primeCount = (int)(inChannel.size()/8L);

        // Verify index for the last prime required is OK
        if(requiredPrimeFrom+totalPrimesRequired-1 > primeCount) {
          totalPrimesRequired = primeCount - requiredPrimeFrom + 1;
          System.out.println("Primes requested go beyond the end of the file. Setting count to "
                             +totalPrimesRequired + ", which will be up to the last prime in the file.");
        }

      // Verify index for the first prime required is OK
      if(requiredPrimeFrom > primeCount) {
        System.out.println("Prime "+ requiredPrimeFrom +" is not in the file. Listing the "+primeCount
                                + " primes in the file.");
        requiredPrimeFrom = 0;
      }
      if(requiredPrimeFrom>0) { 
          inChannel.position(8*(requiredPrimeFrom-1));
          buf = ByteBuffer.allocate(8*totalPrimesRequired);
          inChannel.read(buf); 
          buf.flip();
          LongBuffer longBuffer = buf.asLongBuffer();
          System.out.println("Requested primes are: ");
          StringBuffer line = new StringBuffer(60);     // 6 fields of width 10 to hold primes
          String number = null;
          char[] spaces = {' ', ' ', ' ', ' ',' ', ' ',' ', ' ',' ', ' '};
          for(int i = 0 ; i<longBuffer.limit() ; i++) {
            number = String.valueOf(longBuffer.get());
            line.append(spaces,0,spaces.length-number.length()).append(number);
            if(line.length() == line.capacity()) {
              System.out.println(line.toString());
              line.delete(0,line.length());
            } 
          }
          // Check for partially filled string buffer and print it.
          if(line.length() > 0)
            System.out.println(line.toString());
                 
          System.exit(0);
      }
    } catch(IllegalArgumentException e)  {
        e.printStackTrace();
        System.exit(1);
    } catch(IOException e) {
        e.printStackTrace();
        System.exit(1);
    }

    // If no specific prime is required, output the lot from the file
    if(requiredPrimeFrom == 0) {   
      final int PRIMECOUNT = 6;
      buf = ByteBuffer.allocate(8*PRIMECOUNT);  
      long[] primes = new long[PRIMECOUNT];
      try {
        int primesRead = 0;                              // Number of primes read into the buffer
        while(inChannel.read(buf) != -1) {
         buf.flip();                                     // Flip the buffer ready to get the data
         primesRead = buf.asLongBuffer().limit();        // Get the number of primes in the buffer 
         buf.asLongBuffer().get(primes, 0 , primesRead); // Extract that many primes into the array

          // Display the primes neatly this time using the technique from earlier...
          StringBuffer line = new StringBuffer(60);     // 6 fields of width 10 to hold primes
          String number = null;
          char[] spaces = {' ', ' ', ' ', ' ',' ', ' ',' ', ' ',' ', ' '};
          for(int i = 0 ; i<primesRead ; i++) {
            number = String.valueOf(primes[i]);
            line.append(spaces,0,spaces.length-number.length()).append(number);
            if(line.length() == line.capacity()) {
              System.out.println(line.toString());
              line.delete(0,line.length());
            } 
          }
          // Check for partially filled string buffer and print it.
          if(line.length() > 0)
            System.out.println(line.toString());

          buf.clear();                                   // Clear the buffer for the next read
        }
        System.out.println("\nEOF reached.");
        inFile.close();                                  // Close the stream and the channel
      } catch(IOException e) {
          e.printStackTrace(System.err);
          System.exit(1);
      }
    }
  }
}
