/**
 * 
 */

/**
 * @author Steve
 *
 */
public class PowerCalc {

	/**
	 * 
	 */
	public PowerCalc() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double x = 5.0;
		
		System.out.println(x + " to the power 4 is " + power(x,4));
		System.out.println("10 to the power -2 is " + power(10,-2));
		System.out.println("0.5 to the power 2 is " + power(0.5,2));

	}
	
	static double power(double x, int n)
	{
		if (n > 1)
			return x*power(x,n-1);
		else if (n < 0)
			return 1.0/power(x,-n);
		else
			return n == 0 ? 1.0 : x;					
	}

}
