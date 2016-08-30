/**
 * 
 */

/**
 * @author Steve
 *
 */
public class LetterCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char symbol = 'A';
		symbol = (char)(128.0*Math.random());
		
		if (symbol >= 'A' && symbol <= 'Z')
			System.out.println("You have a capital letter " + symbol);
		else if (symbol >= 'a' && symbol <= 'z')
			System.out.println("you have a small letter " + symbol);
		else if (symbol < 'A')
			System.out.println("The code is less than A but it's not a letter");
		else
			System.out.println("The code is greater than \'A\' but it's not a letter");
			
		
		/*
		if (symbol >= 'A')
		{
			if (symbol <= 'Z')
			{
				System.out.println("You have the capital letter " + symbol);
			}
			else
			{
				if (symbol >= 'a')
				{
					if (symbol <= 'z')
					{
						System.out.println("You have the small letter "+ symbol);
					}
					else
					{
						System.out.println("The code is greater than a but it's not a letter");
					}
				}
				else
				{
					System.out.println("The code is less than a but it's  not a letter");
				}
			}
		}
		else
		{
			System.out.println("The code is less than A but it's not a letter");
		}
*/
	}
	

}
