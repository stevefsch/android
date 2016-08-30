public class NumberCheck 
{
  public static void main(String[] args)
  {
    int number = 0;
    number = 1+(int)(100*Math.random());  // Get a random integer between 1 & 100

    if(number%2 == 0)                  // Test if it is even
    {
      if(number < 50)                  // Output a message if number is < 50
        System.out.println("You have got an even number < 50, " + number);
    }
    else
      System.out.println("You have got an odd number, " + number); // It is odd
  }
}
