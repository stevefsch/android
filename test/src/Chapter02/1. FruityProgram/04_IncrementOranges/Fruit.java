import java.io.IOException;  // For code that delays ending the program

public class Fruit 
{
  public static void main(String[] args) 
  {
    // Declare and initialize three variables
    int numOranges = 5;
    int numApples = 10;
    int numFruit = 0;

    // Increment oranges and calculate the total fruit
    numFruit = ++numOranges + numApples; 

    System.out.println("A totally fruity program");
    // Display the result
    System.out.println("Value of oranges is " + numOranges);
    System.out.println("Total fruit is " + numFruit);

    // Code to delay ending the program
    System.out.println("(press Enter to exit)");
    try 
    {
      System.in.read();               // Read some input from the keyboard
    }
    catch (IOException e)             // Catch the input exception
    {
      return;                         // and just return
    }
  }
}
