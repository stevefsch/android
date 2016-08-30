// Chapter 13 Exercise 1

import java.util.Vector;

public class MorePrimes {
  public static void main(String[] args) {
    Vector primes = new Vector();              // Vector to store primes
    int primesRequired = 20;                   // Number of primes required

    primes.add(new Long(2L));                  // Seed the first prime
    primes.add(new Long(3L));                  // and the second
    long number = 5L;                          // Next integer to be tested
    long divisor = 0L;                         // Stores current trial divisor

    outer:
    for( ; primes.size() < primesRequired; number += 2) {
      // The maximum divisor we need to try is square root of number
      long limit = (long)Math.ceil(Math.sqrt((double)number));

      // Divide by all the primes we have up to limit
      for(int i = 1; i < primes.size() && (divisor = ((Long)(primes.elementAt(i))).longValue()) <= limit; i++) {
        if(number%divisor == 0) {            // Is it an exact divisor?
          continue outer;           	     // Yes, try the next number
        }
      }
      primes.add(new Long(number));;         // We got one!
    }

    for(int i=0; i < primes.size(); i++)
      System.out.println(((Long)(primes.elementAt(i))).longValue());          // Output all the primes
  }
}

