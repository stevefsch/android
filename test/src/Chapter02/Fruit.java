//import java.io.IOException;  // For code that delays ending the program

public class Fruit {
  public static void main(String[] args) {
    // Declare and initialize three variables
    int numOranges = 5;                     // Count of oranges
    int numApples = 10;                     // Count of apples
    int numFruit = 0;                       // Count of fruit

    // Increment oranges and calculate the total fruit
    numFruit = numOranges++ + numApples;

    //numFruit = numOranges + numApples;      // Calculate the total fruit count

    // Display the result
    System.out.println("A totally fruity program");

    // Display the result

    System.out.println("Value of oranges is " + numOranges);

    System.out.println("Total fruit is " + numFruit);
    //                  + " and Oranges = " + numOranges);

    // Code to delay ending the program

    //System.out.println("(press Enter to exit)");
    //try {
    //  System.in.read();               // Read some input from the keyboard

    //} catch (IOException e) {         // Catch the input exception
    //  return;                         // and just return
    //}

  }
}
