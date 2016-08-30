/**
 * 
 */

/**
 * @author Steve
 *
 */
public class TryInitialization {
	
	static int[] values = new int[10];
	
	static 
	{
		System.out.println("Running initialization block.");
		
		for (int i = 0; i < values.length; i++)
		{
			values[i] = (int)(100.0*Math.random());
		}
	}

	/**
	 * 
	 */
	public TryInitialization() {
		// TODO Auto-generated constructor stub
	}
	
	void listVaues()
	{
		System.out.println();
		for (int i = 0; i < values.length; i++)
		{
			System.out.println(" " + values[i]);
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TryInitialization example = new TryInitialization();
		System.out.println("\nFirst object:");
		example.listVaues();
		example = new TryInitialization();
		System.out.println("\nSecond object:");
		example.listVaues();

	}

}
