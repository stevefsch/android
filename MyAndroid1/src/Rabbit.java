/**
 * 
 */

/**
 * @author Steve
 *
 */
public class Rabbit {

	/**
	 * 
	 */
	public Rabbit() {
		// TODO Auto-generated constructor stub
	}

	static private String[] rabbitNames = {"Floppsy","Moppsy","Gnasher","Thumper"};
	static private int[] rabbitNamesCount = new int[rabbitNames.length];
	private String name;
	
	public Rabbit()
	{
		int index = (int) (rabbitNames.length*Math.random());
		name = rabbitNames[index] + (++rabbitNamesCount[index]);
	}
	
	public String toString()
	{
		return name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
