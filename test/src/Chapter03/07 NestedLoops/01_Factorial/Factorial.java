public class Factorial 
{
  public static void main(String[] args) 
  {
    long limit = 20;        // Calculate factorial of integers up to this value
    long factorial = 1;     // Calculate factorial in this variable

    // Loop from 1 to the value of limit
    for(int i = 1; i <= limit; i++)
    {
      factorial = 1;       // Initialize factorial
      int j =2;
      while(j <= i)
        factorial *= j++;
      System.out.println(i + "!" + " is " + factorial);
    }
  }
}
