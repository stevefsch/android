// Chapter 7 Exercise 3

public class TestIterated {
  public static void main(String args[]) {
    try {
      loopy();          // Call the method that will throw the exception.
    }
    catch(IteratedArithmeticException e) {
      System.out.println(e);
    }
  }

  // This method throws an exception - eventually:
  static void loopy() throws IteratedArithmeticException {
    int iteration;               // Iteration counter.
    int result;                  // Stores a result.
    int divisor;                 // Random divisor.

    // Loop will continue until an exception is thrown:
    for(iteration=0;;iteration++)
      try {
        divisor = (int)(100*Math.random());     // Generate a random divisor.
        result = 1000/divisor;                  // If we hit zero - bingo!		
      }
      catch(ArithmeticException e) {
        throw new IteratedArithmeticException(iteration);
      }
  }
}
