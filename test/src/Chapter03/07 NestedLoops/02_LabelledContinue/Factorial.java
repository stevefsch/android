public class Factorial 
{
  public static void main(String[] args) 
  {
    long limit = 20;      // to calculate factorial of integers up to this value
    long factorial = 1;   // factorial will be calculated in this variable

    // Loop from 1 to the value of limit
    OuterLoop:
    for(int i = 1; i <= limit; i++)
    {
      factorial = 1;               // Initialize factorial
      for(int j = 2; j <= i; j++)
      {
        if(i > 10 && i % 2 == 1)
          continue OuterLoop;      // Transfer to the outer loop
        factorial *= j;
      }
      System.out.println(i + "!" + " is " + factorial);
    }
  }
}
