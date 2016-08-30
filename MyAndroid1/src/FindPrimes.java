/**
 * 
 */

/**
 * @author Steve
 *
 */
public class FindPrimes {

	/**
	 * 
	 */
	public FindPrimes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nPrimes = 50;
	
	Outerloop:
		for(int i = 2; ; i++)
		{
			for(int j = 2; j < i; j++)
			{
				if (i % j == 0)
					continue Outerloop;
			}
			
			System.out.println(i);
			
			if (--nPrimes == 0)
				break Outerloop;
				//break;
		}
			
		

	}

}
